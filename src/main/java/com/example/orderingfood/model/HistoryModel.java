package com.example.orderingfood.model;

import javax.persistence.*;

@Entity
@Table(name = "history")
public class HistoryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "order_id") // Указывает на столбец, который связывает сущности
    private OrderModel orderhistory;

    @ManyToOne
    @JoinColumn(name = "user_id") // Указывает на столбец, который связывает сущности
    private UserModel userhistory;

    public HistoryModel() {
    }

    public HistoryModel(long id, OrderModel orderhistory, UserModel userhistory) {
        this.id = id;
        this.orderhistory = orderhistory;
        this.userhistory = userhistory;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public OrderModel getOrderhistory() {
        return orderhistory;
    }

    public void setOrderhistory(OrderModel orderhistory) {
        this.orderhistory = orderhistory;
    }

    public UserModel getUserhistory() {
        return userhistory;
    }

    public void setUserhistory(UserModel userhistory) {
        this.userhistory = userhistory;
    }
}
