package com.example.orderingfood.Repo;

import com.example.orderingfood.model.LogModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepo extends JpaRepository<LogModel, Long> {
}
