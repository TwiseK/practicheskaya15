package com.example.orderingfood.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.util.Collection;

@Entity
@Table(name = "typedish")
public class TypeDishModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Size(max = 30, message = "Длина доолжна быть н более 30 символов")
    @Column(name = "name")
    private String name;

    @OneToMany (mappedBy = "typedish")
    private Collection<DishModel> dish;

    public TypeDishModel() {
    }

    public TypeDishModel(long id, String name, Collection<DishModel> dish) {
        this.id = id;
        this.name = name;
        this.dish = dish;
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

    public Collection<DishModel> getDish() {
        return dish;
    }

    public void setDish(Collection<DishModel> dish) {
        this.dish = dish;
    }
}
