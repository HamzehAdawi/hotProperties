package edu.abhs.hotProperties.controller;

import edu.abhs.hotProperties.entities.Property;
import edu.abhs.hotProperties.entities.User;
import edu.abhs.hotProperties.service.AuthService;
import edu.abhs.hotProperties.service.MessagesService;
import edu.abhs.hotProperties.service.PropertyService;
import edu.abhs.hotProperties.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@Controller
public class PropertyController {

    PropertyService propertyService;
    UserService userService;
    AuthService authService;
    MessagesService  messagesService;

    @Autowired
    public PropertyController(PropertyService propertyService,  UserService userService,  AuthService authService, MessagesService messagesService) {
        this.propertyService = propertyService;
        this.userService = userService;
        this.authService = authService;
        this.messagesService = messagesService;
    }


    @PreAuthorize("hasRole('AGENT')")
    @GetMapping("/properties/manage")
    public String agentManage(Model model) {
        User user = authService.getCurrentUser();
        model.addAttribute("user", user);
        return "manage_properties";
    }

    @PreAuthorize("hasRole('AGENT')")
    @PostMapping("/properties/add")
    public String addProperty(@ModelAttribute("property") Property property, @RequestParam(value = "file", required = false)
    List<MultipartFile> files, Model model) {

        if (property == null) {
            model.addAttribute("fail_message", "Could not add Property. Please try again.");
            return "add_properties";
        }

        try {
            userService.addedProperty(property);
            propertyService.addProperty(property);
            propertyService.addPropertyImages(property, files);

            User user = authService.getCurrentUser();
            model.addAttribute("user", user);
            model.addAttribute("success_message", "Added new property successfully!");
        } catch (Exception e) {
            model.addAttribute("fail_message", e.getMessage());
            return "add_properties";
        }
        return "manage_properties";
    }

    @PreAuthorize("hasRole('AGENT')")
    @GetMapping("/editProperty")
    public String showEditProperty(@RequestParam("title") String title, Model model) {
        Property property = propertyService.getByTitle(title);
        model.addAttribute("property", property);
        model.addAttribute("newProperty", new Property());
        return "edit_property";
    }

    @Transactional
    @PreAuthorize("hasRole('AGENT')")
    @PostMapping("/editProperty")
    public String editProperty(@ModelAttribute("newProperty") Property newProperty,@RequestParam("id") long id,
                               @RequestParam(value = "file", required = false)
                               List<MultipartFile> files, Model model)  {
        User user = authService.getCurrentUser();
        Property property = propertyService.getPropertyById(id);
        propertyService.updateProperty(newProperty, property);
        propertyService.addPropertyImages(property, files);
        model.addAttribute("successMessage", "Property updated successfully!");
        model.addAttribute("user", user);
        return "manage_properties";
    }

    @Transactional
    @PreAuthorize("hasRole('AGENT')")
    @PostMapping("/deletePropertyImage")
    public String deletePropertyImage(@RequestParam("propsid") long id, @RequestParam("imageId") long imageId, Model model) {

        Property property = propertyService.getPropertyById(id);

        propertyService.deletePropertyImage(property, imageId);
        model.addAttribute("property", property);
        model.addAttribute("newProperty", new Property());
        model.addAttribute("successMessage", "Property image deleted successfully");
        return "edit_property";
    }

    @Transactional
    @PreAuthorize("hasRole('AGENT')")
    @PostMapping("/deleteProperty")
    public String deleteProperty(@RequestParam("id") long id, Model model) {
        if (messagesService.propertyHasMessages(propertyService.getPropertyById(id))) {
            model.addAttribute("user", authService.getCurrentUser());
            model.addAttribute("fail_message", "Warning! You have messages for this property.");
            return "manage_properties";
        }

        userService.removeFav(propertyService.getPropertyById(id).getFavList());

        Property property = propertyService.getPropertyById(id);

        propertyService.deletePropertyImages(property);
        userService.removeProperty(property);
        propertyService.deleteProperty(property);

        model.addAttribute("successMessage", "Property removed successfully");
        model.addAttribute("user", authService.getCurrentUser());
        return "manage_properties";
    }

    @PreAuthorize("hasRole('AGENT')")
    @GetMapping("/properties/add")
    public String showAddProperties(Model model) {
        model.addAttribute("property", new Property());
        return "add_properties";
    }


    @GetMapping("/properties/list")
    @PreAuthorize("hasAnyRole('AGENT', 'BUYER', 'ADMIN')")
    public String browseProperties(Model model) {
        List<Property> properties = userService.getAllProperties();
        model.addAttribute("properties", properties);
        model.addAttribute("count", properties.size());
        return "browse_properties";
    }

    @GetMapping("/properties/view/{id}")
    @PreAuthorize("hasAnyRole('AGENT', 'BUYER', 'ADMIN')")
    public String viewProperty(@PathVariable Long id, Model model) {
        User u = authService.getCurrentUser();
        Property property = userService.getPropertyById(id);

        if(userService.isFavorited(u, property))
        {
            model.addAttribute("showRemoveFavoriteButton", true);
        }
        else
        {
            model.addAttribute("showAddFavoriteButton", true);
        }

        model.addAttribute("user", u);
        model.addAttribute("property", property);
        return "property_view";
    }

    @GetMapping("/properties/search")
    @PreAuthorize("hasAnyRole('AGENT', 'BUYER', 'ADMIN')")
    public String viewForm(@RequestParam("zipcode") String zipcode, @RequestParam("minSqFt") String minSqFt,
                           @RequestParam("minPrice") String minPrice, @RequestParam("maxPrice") String maxPrice,
                           @RequestParam("sort") String sort, Model model) {

        List<Property> properties;

        if(minSqFt.isEmpty())
        {
            minSqFt = "0";
        }

        if(sort.equals("lowToHigh"))
        {
            if(minPrice.isEmpty() && maxPrice.isEmpty())
            {
                properties = userService.findPropertyByFiltersNoMinMaxAsc(zipcode, minSqFt);
            }
            else if(minPrice.isEmpty())
            {
                properties = userService.findPropertyByFiltersNoMinAsc(zipcode, minSqFt, maxPrice);
            }
            else if(maxPrice.isEmpty())
            {
                properties = userService.findPropertyByFiltersNoMaxAsc(zipcode, minSqFt, minPrice);
            }
            else
            {
                properties = userService.findPropertyWithAllFilters(zipcode, minSqFt, minPrice, maxPrice);
            }
        }
        else
        {
            if(minPrice.isEmpty() && maxPrice.isEmpty())
            {
                properties = userService.findPropertyByFiltersNoMinMaxDesc(zipcode, minSqFt);
            }
            else if(minPrice.isEmpty())
            {
                properties = userService.findPropertyByFiltersNoMinDesc(zipcode, minSqFt, maxPrice);
            }
            else if(maxPrice.isEmpty())
            {
                properties = userService.findPropertyByFiltersNoMaxDesc(zipcode, minSqFt, minPrice);
            }
            else
            {
                properties = userService.findPropertyWithAllFiltersDesc(zipcode, minSqFt, minPrice, maxPrice);
            }
        }

        model.addAttribute("properties", properties);
        model.addAttribute("count", properties.size());
        return "browse_properties";
    }

}
