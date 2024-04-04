package com.example.orderingfood.Repo;

import com.example.orderingfood.model.DeliveryModel;
import com.example.orderingfood.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepo extends JpaRepository<DeliveryModel, Long> {
    java.lang.Iterable<DeliveryModel> findByDeliverAndOrderdelStatusName(UserModel userModel, String name);
    java.lang.Iterable<DeliveryModel> findByOrderdelStatusName(String name);
}
