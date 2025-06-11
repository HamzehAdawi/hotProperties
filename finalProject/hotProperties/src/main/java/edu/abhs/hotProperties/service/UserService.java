package edu.abhs.hotProperties.service;

import edu.abhs.hotProperties.entities.Favorite;
import edu.abhs.hotProperties.entities.Messages;
import edu.abhs.hotProperties.entities.User;
import edu.abhs.hotProperties.entities.Property;
import org.springframework.ui.Model;

import java.util.List;

public interface UserService {
    void prepareDashboardModel(Model model);
    List<Property> getProperties(User user);
    void addProperty(Property property);
    List<User> getAllUsers();
    void deleteUser(Long userId);
    void addUser(User user);
    boolean emailExists(String email);
    void saveUser(User user);
    List<Property> getAllProperties();
    Property getPropertyById(Long id);
    List<Property> findPropertyWithAllFilters(String zipcode, String minSqFt, String minPrice, String maxPrice);
    List<Property> findPropertyByFiltersNoMinMaxAsc(String zipcode, String minSqFt);
    List<Property> findPropertyByFiltersNoMinAsc(String zipcode, String minSqFt, String maxPrice);
    List<Property> findPropertyByFiltersNoMaxAsc(String zipcode, String minSqFt, String minPrice);
    List<Property> findPropertyByFiltersNoMinMaxDesc(String zipcode, String minSqFt);
    List<Property> findPropertyByFiltersNoMinDesc(String zipcode, String minSqFt, String maxPrice);
    List<Property> findPropertyByFiltersNoMaxDesc(String zipcode, String minSqFt, String minPrice);
    List<Property> findPropertyWithAllFiltersDesc(String zipcode, String minSqFt, String minPrice, String maxPrice);
    List<Favorite> getUsersFavorites(User u);
    Favorite getSpecificFavorite(User u, Long id);
    void removeFavorite(Favorite favorite);
    void addFavorite(Favorite favorite);
    boolean isFavorited(User u, Property property);
    void updateProfile(User newUser);
    void removeProperty(Property property);
    void addedProperty(Property property);
    User getUserById(long id);
    List<Messages> getAgentMessages();
    List<Messages> getBuyerMessages();

    void removeFav(List<Favorite> favorites);
}
