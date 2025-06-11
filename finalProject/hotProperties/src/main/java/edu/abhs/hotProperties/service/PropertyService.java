package edu.abhs.hotProperties.service;

import edu.abhs.hotProperties.entities.Favorite;
import edu.abhs.hotProperties.entities.Messages;
import edu.abhs.hotProperties.entities.Property;
import edu.abhs.hotProperties.entities.User;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

public interface PropertyService {
    void addProperty(Property property);
    void addPropertyImages(Property property, List<MultipartFile> files);
    Property getByTitle(String title);
    void deletePropertyImage(Property property, long imageId);
    Property getPropertyById(long id);
    void updateProperty(Property newProperty, Property property);
    long getAgent (Property property);
    void deletePropertyImages(Property property);
    void deleteProperty(Property property);

    void removeFav(List<Favorite> favorites);

    void removeMessages(List<Messages> messages);
}
