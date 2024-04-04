package com.example.orderingfood.Repo;

import com.example.orderingfood.model.FeedbackModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepo  extends JpaRepository<FeedbackModel, Long> {
}
