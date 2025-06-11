package edu.abhs.hotProperties.controller;

import edu.abhs.hotProperties.entities.Favorite;
import edu.abhs.hotProperties.entities.Property;
import edu.abhs.hotProperties.entities.User;
import edu.abhs.hotProperties.service.AuthService;
import edu.abhs.hotProperties.service.PropertyService;
import edu.abhs.hotProperties.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class FavoritesController {

    AuthService authService;
    PropertyService propertyService;
    UserService userService;

    @Autowired
    public FavoritesController(AuthService authService,  PropertyService propertyService, UserService userService) {
        this.authService = authService;
        this.propertyService = propertyService;
        this.userService = userService;
    }


    @GetMapping("/favorites")
    @PreAuthorize("hasAnyRole('AGENT', 'BUYER', 'ADMIN')")
    public String viewFavorites(Model model) {
        User u = authService.getCurrentUser();

        List<Favorite> favorites = userService.getUsersFavorites(u);
        List<Property> properties = new ArrayList<>();

        for(Favorite fav : favorites)
        {
            properties.add(fav.getProperty());
        }

        model.addAttribute("user", u);
        model.addAttribute("properties", properties);
        return "favorites";
    }

    @GetMapping("/favorites/remove/{id}")
    @PreAuthorize("hasAnyRole('AGENT', 'BUYER', 'ADMIN')")
    public String removeFavorite(@PathVariable Long id, Model model) {
        User u = authService.getCurrentUser();

        Favorite favorite = userService.getSpecificFavorite(u, id);

        u.getFavList().remove(favorite);
        propertyService.getPropertyById(id).getFavList().remove(favorite);

        userService.removeFavorite(favorite);

        return "redirect:/favorites";
    }

    @GetMapping("/properties/remove/favorites/{id}")
    @PreAuthorize("hasAnyRole('AGENT', 'BUYER', 'ADMIN')")
    public String removeFavoriteFromPropertyPage(@PathVariable Long id, Model model) {
        User u = authService.getCurrentUser();

        Favorite favorite = userService.getSpecificFavorite(u, id);

        u.getFavList().remove(favorite);
        propertyService.getPropertyById(id).getFavList().remove(favorite);

        userService.removeFavorite(favorite);

        return "redirect:/properties/view/" + id;
    }

    @PostMapping("/favorites/add/{id}")
    @PreAuthorize("hasAnyRole('AGENT', 'BUYER', 'ADMIN')")
    public String addFavorite(@PathVariable Long id, Model model) {
        User u = authService.getCurrentUser();
        Property p = userService.getPropertyById(id);

        boolean favorited = false;

        List<Favorite> favorites = userService.getUsersFavorites(u);

        //Check if user already has property favored
        for(Favorite fav: favorites)
        {
            if(fav.getProperty().getId() == id)
            {
                favorited = true;
            }
        }

        if(!favorited)
        {
            Favorite favorite = new Favorite(u, p);
            u.getFavList().add(favorite);
            propertyService.getPropertyById(id).getFavList().add(favorite);
            userService.addFavorite(favorite);
        }

        return "redirect:/properties/view/" + id;
    }
}
