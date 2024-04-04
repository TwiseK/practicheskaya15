package com.example.orderingfood.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.util.Collection;
import java.util.Set;

@Entity
@Table (name = "users")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Size(max = 30, message = "Длина доолжна быть н более 30 символов")
    @Column (name = "name")
    private String name;

    @Size(max = 30, message = "Длина должна быть не более 30 символов")
    @Column (name = "surname")
    private String surname;

    @Size(max = 30, message = "Длина должна быть не более 30 символов")
    @Column (name = "middle")
    private String middle;


    @NotBlank(message = "Name is required")
    @Size(max = 11, message = "Длина должна быть не более 11 символов")
    @Column (name = "login", unique = true)
    private String login;



    @Column (name = "password")
    private String password;


    @Size(max = 255, message = "Длина должна быть не более 255 символов")
    @Column (name = "address")
    private String address;

    @Column (name = "dateBirth")
    private String dateBirth;


    @Size(max = 12, message = "Длина должна быть не более 12 символов")
    @Column(name = "phone")
    private String phone;

    @Size(max = 30, message = "Длина должна быть не более 30 символов")
    @Column (name = "post")
    private String post;

    @Column(name = "active")
    private boolean active;

    @OneToMany (mappedBy = "deliver")
    private Collection<DeliveryModel> del;

    @OneToMany (mappedBy = "basket")
    private Collection<BasketModel> basket;

    @OneToMany (mappedBy = "feedback")
    private Collection<FeedbackModel> feedback;

    @OneToMany (mappedBy = "user")
    private Collection<DeliveryModel> user;

    @OneToMany (mappedBy = "userhistory")
    private Collection<HistoryModel> history;

    @ElementCollection(targetClass = roleEnum.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<roleEnum> roles;

    public UserModel() {
    }

    public UserModel(long id, String name, String surname, String middle, String login, String password, String address, String dateBirth, String phone, String post, boolean active, Collection<DeliveryModel> del, Collection<BasketModel> basket, Collection<FeedbackModel> feedback, Collection<DeliveryModel> user, Collection<HistoryModel> history, Set<roleEnum> roles) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.middle = middle;
        this.login = login;
        this.password = password;
        this.address = address;
        this.dateBirth = dateBirth;
        this.phone = phone;
        this.post = post;
        this.active = active;
        this.del = del;
        this.basket = basket;
        this.feedback = feedback;
        this.user = user;
        this.history = history;
        this.roles = roles;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMiddle() {
        return middle;
    }

    public void setMiddle(String middle) {
        this.middle = middle;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(String dateBirth) {
        this.dateBirth = dateBirth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Collection<DeliveryModel> getDel() {
        return del;
    }

    public void setDel(Collection<DeliveryModel> del) {
        this.del = del;
    }

    public Collection<BasketModel> getBasket() {
        return basket;
    }

    public void setBasket(Collection<BasketModel> basket) {
        this.basket = basket;
    }

    public Collection<FeedbackModel> getFeedback() {
        return feedback;
    }

    public void setFeedback(Collection<FeedbackModel> feedback) {
        this.feedback = feedback;
    }

    public Collection<DeliveryModel> getUser() {
        return user;
    }

    public void setUser(Collection<DeliveryModel> user) {
        this.user = user;
    }

    public Collection<HistoryModel> getHistory() {
        return history;
    }

    public void setHistory(Collection<HistoryModel> history) {
        this.history = history;
    }

    public Set<roleEnum> getRoles() {
        return roles;
    }

    public void setRoles(Set<roleEnum> roles) {
        this.roles = roles;
    }
}
