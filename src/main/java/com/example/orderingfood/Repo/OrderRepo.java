package com.example.orderingfood.Repo;

import com.example.orderingfood.model.*;
import org.hibernate.criterion.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<OrderModel, Long> {
    java.lang.Iterable<OrderModel> findByStatusName (String name);

    //OrderModel findByBasketBasket(UserModel userModel);

    Iterable<OrderModel> findAllByBasketBasket(UserModel userModel);


}
