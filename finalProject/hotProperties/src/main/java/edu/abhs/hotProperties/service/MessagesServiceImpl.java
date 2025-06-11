package edu.abhs.hotProperties.service;

import edu.abhs.hotProperties.entities.Messages;
import edu.abhs.hotProperties.entities.Property;
import edu.abhs.hotProperties.entities.User;
import edu.abhs.hotProperties.repository.MessagesRepository;
import edu.abhs.hotProperties.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessagesServiceImpl implements  MessagesService {

    MessagesRepository messagesRepository;
    PropertyRepository propertyRepository;

    @Autowired
    public MessagesServiceImpl(MessagesRepository messagesRepository,  PropertyRepository propertyRepository) {
        this.messagesRepository = messagesRepository;
        this.propertyRepository = propertyRepository;
    }

    @Override
    public void addMessages(Messages messages) {
        messagesRepository.save(messages);
    }

    @Override
    public Messages getMessagesById(long id) {
        return messagesRepository.findById(id);
    }

    @Override
    public void deleteMessages(User user, Property property, Messages messages) {

        property.removeMessage(messages);
        user.removeMessage(messages);
        messagesRepository.deleteById(messages.getId());
    }

    @Override
    public void reply(String reply, Messages messages) {
        messages.setReply(reply);
        messagesRepository.save(messages);
    }

    @Override
    public boolean propertyHasMessages(Property property) {
        List<Messages> messageList = property.getMessageList();
        for (Messages messages : messageList) {
            if (messages != null) {
                return true;
            }
        }
        return false;
    }


}
