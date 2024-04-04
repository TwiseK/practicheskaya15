package com.example.orderingfood.Repo;

import com.example.orderingfood.model.BasketModel;
import com.example.orderingfood.model.TypeDelModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeDelRepo extends JpaRepository<TypeDelModel, Long> {

    TypeDelModel findByName(String name);
}
