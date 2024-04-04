package com.example.orderingfood.model;

import javax.persistence.*;
import org.apache.catalina.User;

import java.util.Collection;

@Entity
@Table(name = "delivery")
public class DeliveryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    @ManyToOne
    @JoinColumn(name = "user_id") // Указывает на столбец, который связывает сущности
    private UserModel deliver;

    @ManyToOne
    @JoinColumn(name = "client_id") // Указывает на столбец, который связывает сущности
    private UserModel user;

    @ManyToOne
    @JoinColumn(name = "order_id") // Указывает на столбец, который связывает сущности
    private OrderModel orderdel;

    @OneToMany (mappedBy = "delivery")
    private Collection<FeedbackModel> feedback;


    public DeliveryModel(){}

    public DeliveryModel(long id, UserModel deliver, UserModel user, OrderModel orderdel, Collection<FeedbackModel> feedback) {
        this.id = id;
        this.deliver = deliver;
        this.user = user;
        this.orderdel = orderdel;
        this.feedback = feedback;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserModel getDeliver() {
        return deliver;
    }

    public void setDeliver(UserModel deliver) {
        this.deliver = deliver;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public OrderModel getOrderdel() {
        return orderdel;
    }

    public void setOrderdel(OrderModel orderdel) {
        this.orderdel = orderdel;
    }

    public Collection<FeedbackModel> getFeedback() {
        return feedback;
    }

    public void setFeedback(Collection<FeedbackModel> feedback) {
        this.feedback = feedback;
    }
}
