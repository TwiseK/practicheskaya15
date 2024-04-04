package com.example.orderingfood.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.util.Collection;

@Entity
@Table (name = "dish")
public class DishModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Min(0)
    @Column (name = "cost")
    private int cost;

    @Size(max = 30, message = "Длина должна быть не более 30 символов")
    @Column (name = "name")
    private String name;

    @Size(max = 400, message = "Длина должна быть не более 400 символов")
    @Column (name = "info")
    private String info;

    @Column (name = "image")
    private String image;

    @OneToMany (mappedBy = "dish")
    private Collection<SelectedDishModel> selecteddDish;

    @ManyToOne
    @JoinColumn(name = "typedish_id") // Указывает на столбец, который связывает сущности
    private TypeDishModel typedish;

    public DishModel(){
    }

    public DishModel(long id, int cost, String name, String info, String image, Collection<SelectedDishModel> selecteddDish, TypeDishModel typedish) {
        this.id = id;
        this.cost = cost;
        this.name = name;
        this.info = info;
        this.image = image;
        this.selecteddDish = selecteddDish;
        this.typedish = typedish;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Collection<SelectedDishModel> getSelecteddDish() {
        return selecteddDish;
    }

    public void setSelecteddDish(Collection<SelectedDishModel> selecteddDish) {
        this.selecteddDish = selecteddDish;
    }

    public TypeDishModel getTypedish() {
        return typedish;
    }

    public void setTypedish(TypeDishModel typedish) {
        this.typedish = typedish;
    }
}
