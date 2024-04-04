package com.example.orderingfood.Repo;

import com.example.orderingfood.controller.AdminController;
import com.example.orderingfood.model.BasketModel;
import com.example.orderingfood.model.StatusModel;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;

import java.util.logging.Logger;

public interface StatusRepo extends JpaRepository<StatusModel, Long> {
    StatusModel findByName (String status);

}
