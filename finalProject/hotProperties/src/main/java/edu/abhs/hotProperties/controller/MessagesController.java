package edu.abhs.hotProperties.controller;

import edu.abhs.hotProperties.entities.Messages;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MessagesController {

    MessagesService messagesService;
    AuthService authService;
    PropertyService propertyService;
    UserService userService;

    @Autowired
    MessagesController(MessagesService messagesService, AuthService authService, PropertyService propertyService,  UserService userService) {
        this.messagesService = messagesService;
        this.authService = authService;
        this.propertyService = propertyService;
        this.userService = userService;
    }

    @PreAuthorize("hasRole('AGENT')")
    @PostMapping("/deleteMessage")
    @Transactional
    public String deleteMessage(@RequestParam("id") long id) {
        Messages message = messagesService.getMessagesById(id);
        Property property = message.getProperty();
        User user = message.getSender();
        messagesService.deleteMessages(user, property, message);

        return "redirect:/messages";

    }

    @PreAuthorize("hasRole('BUYER')")
    @PostMapping("/deleteMessageBuyer")
    @Transactional
    public String deleteMessageBuyer(@RequestParam("id") long id) {
        Messages message = messagesService.getMessagesById(id);
        Property property = message.getProperty();
        User user = message.getSender();
        messagesService.deleteMessages(user, property, message);

        return "redirect:/messagesBuyer";

    }

    @PreAuthorize("hasRole('BUYER')")
    @PostMapping("/buyer/sendMessageToAgent")
    public String sendMessageToAgent(@RequestParam("msg") String msg ,@RequestParam("prop") long id, Model model) {
        if (propertyService.getPropertyById(id).getUser() == null) {
            Property prop = propertyService.getPropertyById(id);
            model.addAttribute("user", authService.getCurrentUser());
            model.addAttribute("property", prop);
            model.addAttribute("fail_message", "Could not send message! This property has no Agent.");
            return "property_view";
        }

        Property prop = propertyService.getPropertyById(id);
        long agentId = propertyService.getAgent(prop);
        User agent = userService.getUserById(agentId);

        Messages sentMessages = new Messages(msg);

        agent.addMessage(sentMessages);
        sentMessages.setSender(authService.getCurrentUser());

        prop.addMessage(sentMessages);
        sentMessages.setProperty(prop);
        messagesService.addMessages(sentMessages);

        model.addAttribute("sent_agent_worked", "Message sent to agent!");
        model.addAttribute("user", authService.getCurrentUser());
        model.addAttribute("property", prop);

        if (userService.isFavorited(authService.getCurrentUser(), prop)) {
            model.addAttribute("showRemoveFavoriteButton", true);
        } else {
            model.addAttribute("showAddFavoriteButton", true);
        }
        return "property_view";
    }


    @PreAuthorize("hasRole('AGENT')")
    @GetMapping("/messages")
    public String showMessages(Model model) {

        List<Messages> messages = userService.getAgentMessages();
        User user = authService.getCurrentUser();

        model.addAttribute("user", user);
        model.addAttribute("messages", messages);
        return "messages";
    }

    @PreAuthorize("hasRole('AGENT')")
    @PostMapping("/replyToBuyer")
    public String replyToBuyer(@RequestParam("reply") String reply, @RequestParam("messageId") long messageId, Model model) {
        Messages message = messagesService.getMessagesById(messageId);
        messagesService.reply(reply, message);
        model.addAttribute("successMessage", "Message replied sent!");


        List<Messages> messages = userService.getAgentMessages();
        model.addAttribute("user", authService.getCurrentUser());
        model.addAttribute("messages", messages);
        model.addAttribute("message_sent", "Reply sent to Buyer");

        return "messages";
    }

    @PreAuthorize("hasRole('BUYER')")
    @GetMapping("/messagesBuyer")
    public String showMessagesBuyer(Model model) {

        List<Messages> messages = userService.getBuyerMessages();
        model.addAttribute("messages", messages);
        return "messagesBuyer";
    }

    @PreAuthorize("hasRole('AGENT')")
    @GetMapping("/viewMessage")
    public String viewMessage(@RequestParam("id") long id, Model model) {
        Messages message = messagesService.getMessagesById(id);
        model.addAttribute("message", message);
        return "agentViewMessage";
    }



}
