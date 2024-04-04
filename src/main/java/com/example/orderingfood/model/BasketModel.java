package com.example.orderingfood.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import java.util.Collection;

@Entity
@Table(name = "basket")
public class BasketModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "num")
    private String num;

    @Min(0)
    @Column(name = "sum")
    private int sum;

    @Column(name = "active")
    private boolean active;

    @OneToMany (mappedBy = "basket")
    private Collection<OrderModel> order;

    @OneToMany (mappedBy = "basket")
    private Collection<SelectedDishModel> selectedDish;

    @ManyToOne
    @JoinColumn(name = "user_id") // Указывает на столбец, который связывает сущности
    private UserModel basket;

    public BasketModel(){}

    public BasketModel(long id, String num, int sum, boolean active, Collection<OrderModel> order, Collection<SelectedDishModel> selectedDish, UserModel basket) {
        this.id = id;
        this.num = num;
        this.sum = sum;
        this.active = active;
        this.order = order;
        this.selectedDish = selectedDish;
        this.basket = basket;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public Collection<OrderModel> getOrder() {
        return order;
    }

    public void setOrder(Collection<OrderModel> order) {
        this.order = order;
    }

    public Collection<SelectedDishModel> getSelectedDish() {
        return selectedDish;
    }

    public void setSelectedDish(Collection<SelectedDishModel> selectedDish) {
        this.selectedDish = selectedDish;
    }

    public UserModel getBasket() {
        return basket;
    }

    public void setBasket(UserModel basket) {
        this.basket = basket;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
