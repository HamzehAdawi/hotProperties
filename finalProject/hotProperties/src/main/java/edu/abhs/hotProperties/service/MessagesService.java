package edu.abhs.hotProperties.service;

import edu.abhs.hotProperties.entities.Messages;
import edu.abhs.hotProperties.entities.Property;
import edu.abhs.hotProperties.entities.User;


public interface MessagesService {
    void addMessages(Messages messages);
    Messages getMessagesById(long id);
    void deleteMessages(User user, Property property, Messages messages);
    void reply(String reply, Messages messages);
    boolean propertyHasMessages(Property property);
}
