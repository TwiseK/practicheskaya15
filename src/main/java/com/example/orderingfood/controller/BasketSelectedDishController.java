package com.example.orderingfood.controller;

import com.example.orderingfood.Repo.BasketRepo;
import com.example.orderingfood.Repo.SelectedDishRepo;
import com.example.orderingfood.model.BasketModel;
import com.example.orderingfood.model.SelectedDishModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manager/manager_basket")
@PreAuthorize("hasAnyAuthority('MANAGER')")
public class BasketSelectedDishController {

    @Autowired
    public BasketRepo basketRepo;
    @Autowired
    public SelectedDishRepo selectedDishRepo;

    @GetMapping()
    public String listBasketSelectedDish(Model model) {
        Iterable<BasketModel> basketModels = basketRepo.findAll();
        model.addAttribute("baskets", basketModels);
        Iterable<SelectedDishModel> selectedDishModels = selectedDishRepo.findAll();
        model.addAttribute("selects", selectedDishModels);
        return "manager/manager_basket/all";
    }
}
