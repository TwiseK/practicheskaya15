package com.example.orderingfood.Repo;

import com.example.orderingfood.model.BasketModel;
import com.example.orderingfood.model.DishModel;
import com.example.orderingfood.model.TypeDishModel;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;

import java.util.logging.Logger;

public interface DishRepo extends JpaRepository<DishModel, Long> {
    Iterable<DishModel> findByTypedish(TypeDishModel typeDishModel);
}
