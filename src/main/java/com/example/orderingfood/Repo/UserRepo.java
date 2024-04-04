package com.example.orderingfood.Repo;

import com.example.orderingfood.model.DeliveryModel;
import com.example.orderingfood.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo  extends JpaRepository<UserModel, Long> {
    UserModel findByLogin(String login);
    java.lang.Iterable <UserModel> findByPost(String post);

}


