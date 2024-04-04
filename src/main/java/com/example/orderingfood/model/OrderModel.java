package com.example.orderingfood.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.crypto.Data;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "orders")
public class OrderModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Column(name="date_time",columnDefinition="TIMESTAMP")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime dateTime;

    @Min(0)
    @Column (name = "cost")
    private int cost;

    @OneToMany (mappedBy = "orderdel")
    private Collection<DeliveryModel> del;

    @OneToMany (mappedBy = "orderhistory")
    private Collection<HistoryModel> history;

    @ManyToOne
    @JoinColumn(name = "status_id") // Указывает на столбец, который связывает сущности
    private StatusModel status;

    @ManyToOne
    @JoinColumn(name = "typedel_id") // Указывает на столбец, который связывает сущности
    private TypeDelModel typedel;

    @ManyToOne
    @JoinColumn(name = "basket_id") // Указывает на столбец, который связывает сущности
    private BasketModel basket;

    public OrderModel() {
    }

    public OrderModel(long id, LocalDateTime dateTime, int cost, Collection<DeliveryModel> del, Collection<HistoryModel> history, StatusModel status, TypeDelModel typedel, BasketModel basket) {
        this.id = id;
        this.dateTime = dateTime;
        this.cost = cost;
        this.del = del;
        this.history = history;
        this.status = status;
        this.typedel = typedel;
        this.basket = basket;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Collection<DeliveryModel> getDel() {
        return del;
    }

    public void setDel(Collection<DeliveryModel> del) {
        this.del = del;
    }

    public Collection<HistoryModel> getHistory() {
        return history;
    }

    public void setHistory(Collection<HistoryModel> history) {
        this.history = history;
    }

    public StatusModel getStatus() {
        return status;
    }

    public void setStatus(StatusModel status) {
        this.status = status;
    }

    public TypeDelModel getTypedel() {
        return typedel;
    }

    public void setTypedel(TypeDelModel typedel) {
        this.typedel = typedel;
    }

    public BasketModel getBasket() {
        return basket;
    }

    public void setBasket(BasketModel basket) {
        this.basket = basket;
    }
}
