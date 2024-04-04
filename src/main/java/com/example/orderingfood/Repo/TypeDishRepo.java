package com.example.orderingfood.Repo;

import com.example.orderingfood.model.BasketModel;
import com.example.orderingfood.model.TypeDishModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeDishRepo extends JpaRepository<TypeDishModel, Long> {

    TypeDishModel findByName(String name);

}
