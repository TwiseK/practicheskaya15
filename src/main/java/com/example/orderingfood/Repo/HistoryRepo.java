package com.example.orderingfood.Repo;

import com.example.orderingfood.model.BasketModel;
import com.example.orderingfood.model.HistoryModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepo extends JpaRepository<HistoryModel, Long> {
}
