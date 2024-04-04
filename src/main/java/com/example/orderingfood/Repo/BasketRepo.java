package com.example.orderingfood.Repo;

import com.example.orderingfood.model.BasketModel;
import com.example.orderingfood.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketRepo extends JpaRepository<BasketModel, Long> {
    BasketModel findByBasketAndActive(UserModel userModel, boolean bool);

    BasketModel findByBasket(UserModel userModel);
}
