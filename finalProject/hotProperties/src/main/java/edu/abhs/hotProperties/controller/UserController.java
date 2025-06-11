package edu.abhs.hotProperties.controller;

import edu.abhs.hotProperties.entities.User;
import edu.abhs.hotProperties.service.*;
import edu.abhs.hotProperties.service.UserService;
import edu.abhs.hotProperties.service.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    UserService userService;
    AuthService authService;
    PasswordEncoder passwordEncoder;
    PropertyService propertyService;
    MessagesService messagesService;


    @Autowired
    public UserController(UserService userService, AuthService authService, PasswordEncoder passwordEncoder,
                          PropertyService propertyService,  MessagesService messagesService) {
        this.userService = userService;
        this.authService = authService;
        this.passwordEncoder = passwordEncoder;
        this.propertyService = propertyService;
        this.messagesService = messagesService;
    }

    @GetMapping({"/login", "/"})
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login" )
    public String processLogin(@ModelAttribute("user") User user, HttpServletResponse response, Model model) {
        try {
            Cookie jwtCookie = authService.loginAndCreateJwtCookie(user);
            response.addCookie(jwtCookie);
            return "redirect:/dashboard";
        } catch (BadCredentialsException e) {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String processRegistration(@ModelAttribute("user") User user, String confirmPassword, Model model) {
        try {
            // Validate password match
            if (!user.getPassword().equals(confirmPassword)) {
                model.addAttribute("errorMessage", "Passwords do not match");
                return "register";
            }

            // Check if email already exists
            if (userService.emailExists(user.getEmail())) {
                model.addAttribute("errorMessage", "Email already registered");
                return "register";
            }

            // Set createdAt timestamp
            user.setCreatedAt(new java.sql.Timestamp(System.currentTimeMillis()));

            // Encode password and save user
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRole(User.Role.BUYER);
            userService.saveUser(user);

            model.addAttribute("successMessage", "Registration successful! Please login.");
            return "redirect:/login";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Registration failed: " + e.getMessage());
            return "register";
        }
    }

    @GetMapping("/dashboard")
    @PreAuthorize("isAuthenticated()")
    public String showDashboard(Model model) {
        userService.prepareDashboardModel(model);
        return "dashboard";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/myProfile")
    public String showMyProfile(Model model) {
        model.addAttribute("user", authService.getCurrentUser());
        return "my_profile";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/editProfile")
    public String editProfile(Model model) {
        model.addAttribute("newUser", new User());
        model.addAttribute("oldUser", authService.getCurrentUser());
        return "edit_profile";
    }

    @Transactional
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/editProfile")
    public String editedProfile(@ModelAttribute("newUser") User newUser, Model model) {

        try {
            userService.updateProfile(newUser);
            model.addAttribute("oldUser", authService.getCurrentUser());
            model.addAttribute("newUser", new User());
            model.addAttribute("update", "Name Changed successfully!");


        } catch (Exception e) {
            model.addAttribute("oldUser", authService.getCurrentUser());
            model.addAttribute("newUser", new User());
            model.addAttribute("failed", e.getMessage());
        }
        return "edit_profile";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/users/create-agent")
    public String showCreateAgent(Model model) {
        model.addAttribute("user", new User());
        return "add_agent";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin/users/create-agent")
    public String createAgent(@Valid @ModelAttribute("user") User user, BindingResult result, RedirectAttributes redirectAttributes) {
        try {
            if (result.hasErrors()) {
                return "add_agent";
            } else {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                userService.addUser(user);
                redirectAttributes.addFlashAttribute("successMessage", "Agent created successfully");
                return "redirect:/dashboard";
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("failureMessage", "Agent creation failed: " + e.getMessage());
            return "redirect:/admin/users/create-agent";
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/users")
    public String showAllUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "all_users";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/users/delete/{userId}")
    public String handleDeleteUser(@PathVariable("userId") Long userId, Model model,
                                   RedirectAttributes redirectAttributes) {
        if(!userService.getUserById(userId).getPropertyList().isEmpty())
        {
            redirectAttributes.addFlashAttribute("errorMessage", "Agent has properties. Cannot delete agent.");
            return "redirect:/admin/users";
        }
        else if(userService.getUserById(userId).getRole() == User.Role.ADMIN)
        {
            redirectAttributes.addFlashAttribute("errorMessage", "Cannot remove users with Admin role");
            return "redirect:/admin/users";
        }
        propertyService.removeFav(userService.getUserById(userId).getFavList());
        propertyService.removeMessages(userService.getUserById(userId).getMessageList());

        userService.deleteUser(userId);
        redirectAttributes.addFlashAttribute("successMessage", "User deleted successfully");
        return "redirect:/admin/users";
    }

}
