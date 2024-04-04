package com.example.orderingfood.Repo;

import com.example.orderingfood.model.BasketModel;
import com.example.orderingfood.model.DishModel;
import com.example.orderingfood.model.SelectedDishModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SelectedDishRepo extends JpaRepository<SelectedDishModel, Long> {
    SelectedDishModel findByDish(DishModel dishModel);
    Iterable <SelectedDishModel> findByBasket(BasketModel basketModel);
}
