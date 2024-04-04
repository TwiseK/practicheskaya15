package com.example.orderingfood.model;

import javax.persistence.*;
import javax.validation.constraints.Min;

import java.util.Collection;

@Entity
@Table (name = "selecteddish")
public class SelectedDishModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Min(0)
    @Column(name = "cost")
    private int cost;


    @ManyToOne
    @JoinColumn(name = "basket_id") // Указывает на столбец, который связывает сущности
    private BasketModel basket;

    @ManyToOne
    @JoinColumn(name = "dish_id") // Указывает на столбец, который связывает сущности
    private DishModel dish;

    public SelectedDishModel() {
    }

    public SelectedDishModel(long id, int cost, BasketModel basket, DishModel dish) {
        this.id = id;
        this.cost = cost;
        this.basket = basket;
        this.dish = dish;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public BasketModel getBasket() {
        return basket;
    }

    public void setBasket(BasketModel basket) {
        this.basket = basket;
    }

    public DishModel getDish() {
        return dish;
    }

    public void setDish(DishModel dish) {
        this.dish = dish;
    }
}
