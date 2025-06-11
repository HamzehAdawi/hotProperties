package edu.abhs.hotProperties.service;

import edu.abhs.hotProperties.entities.Favorite;
import edu.abhs.hotProperties.entities.Messages;
import edu.abhs.hotProperties.entities.Property;
import edu.abhs.hotProperties.entities.PropertyImage;
import edu.abhs.hotProperties.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class PropertyServiceImpl implements PropertyService {


    PropertyRepository propertyRepository;
    PropertyImageRepository propertyImageRepository;
    FavoriteRepository favoriteRepository;
    MessagesRepository messagesRepository;
    UserRepository userRepository;

    @Autowired
    public PropertyServiceImpl(PropertyRepository propertyRepository, PropertyImageRepository propertyImageRepository,
                               UserRepository userRepository,  FavoriteRepository favoriteRepository,  MessagesRepository messagesRepository) {
        this.propertyRepository = propertyRepository;
        this.propertyImageRepository = propertyImageRepository;
        this.favoriteRepository = favoriteRepository;
        this.messagesRepository = messagesRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void addProperty(Property property) {
        propertyRepository.save(property);

    }

    @Override
    public void addPropertyImages(Property property, List<MultipartFile> files) {

        for(MultipartFile file: files) {
            try {
                Path destination = Paths.get("src/main/resources/static/images", file.getOriginalFilename());
                file.transferTo(destination);

                PropertyImage propertyImage = new PropertyImage(file.getOriginalFilename());
                property.addPropertyImage(propertyImage);
                propertyImage.setProperty(property);
                propertyImageRepository.save(propertyImage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Property getByTitle(String title) {
        return propertyRepository.findPropertyByTitle(title);
    }

    @Override
    public void deletePropertyImage(Property property, long imageId) {
        PropertyImage propertyImage =  propertyImageRepository.findById(imageId);
        property.removePropertyImage(propertyImage);
        propertyImageRepository.delete(propertyImage);
        propertyRepository.save(property);
    }

    @Override
    public Property getPropertyById(long id) {
        return propertyRepository.findPropertyById(id);
    }

    @Override
    public void updateProperty(Property newProperty, Property property) {
        property.setTitle(newProperty.getTitle());
        property.setPrice(newProperty.getPrice());
        property.setLocation(newProperty.getLocation());
        property.setSize(newProperty.getSize());
        property.setDescription(newProperty.getDescription());
        propertyRepository.save(property);

    }

    @Override
    public long getAgent(Property property) {
        return propertyRepository.findUserIdByPropertyId(property.getId());
    }

    @Override
    public void deletePropertyImages(Property property) {
        List<PropertyImage> images = property.getPropertyImages();
        propertyImageRepository.deleteAll(images);
    }

    @Override
    public void deleteProperty(Property property) {
        Favorite favorite = favoriteRepository.findByProperty(property);
        if (favorite != null) {
            favoriteRepository.delete(favorite);
        }
        if (property.getMessageList() != null) {
            property.removeAllMessages();
            messagesRepository.deleteByPropertyId(property.getId());
        }
        propertyRepository.delete(property);
    }

    @Override
    public void removeFav(List<Favorite> favorites) {
        for(Favorite fav: favorites)
        {
            fav.getProperty().getFavList().remove(fav);
        }
    }

    @Override
    public void removeMessages(List<Messages> messages) {
        for(Messages m: messages)
        {
            m.getProperty().getMessageList().remove(m);
        }
    }

}
