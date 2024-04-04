package com.example.orderingfood.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.util.Collection;

@Entity
@Table(name = "status")
public class StatusModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Size(max = 30, message = "Длина должна быть не более 30 символов")
    @Column(name = "name")
    private String name;

    @OneToMany (mappedBy = "status")
    private Collection<OrderModel> order;

    public StatusModel() {
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

    public Collection<OrderModel> getOrder() {
        return order;
    }

    public void setOrder(Collection<OrderModel> order) {
        this.order = order;
    }
}
