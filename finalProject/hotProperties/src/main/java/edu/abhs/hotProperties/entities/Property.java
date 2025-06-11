package edu.abhs.hotProperties.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false, length = 5000)
    private String description;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private Integer size;

    @OneToMany(mappedBy = "property", cascade = CascadeType.PERSIST, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<PropertyImage> propertyImages = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Messages> messageList = new ArrayList<>();

    public List<Favorite> getFavList() {
        return favList;
    }

    public void setFavList(List<Favorite> favList) {
        this.favList = favList;
    }

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Favorite> favList = new ArrayList<>();

    public Property(String title, double price, String description, String location, Integer size) {
        this.title = title;
        this.price = price;
        this.description = description;
        this.location = location;
        this.size = size;
    }

    public Property () {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public List<PropertyImage> getPropertyImages() {
        return propertyImages;
    }

    public void setPropertyImages(List<PropertyImage> propertyImages) {
        this.propertyImages = propertyImages;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void addPropertyImage(PropertyImage propertyImage) {
        this.propertyImages.add(propertyImage);
    }

    public void removePropertyImage(PropertyImage propertyImage) {
        this.propertyImages.remove(propertyImage);
    }

    public List<Messages> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Messages> messageList) {
        this.messageList = messageList;
    }

    public void addMessage(Messages messages) {
        this.messageList.add(messages);
    }

    public void removeMessage(Messages messages) {
        this.messageList.remove(messages);
    }

    public void removeAllMessages() {
        this.messageList.clear();
    }


}
