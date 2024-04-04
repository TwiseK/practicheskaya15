package com.example.orderingfood.controller;

import com.example.orderingfood.Repo.*;
import com.example.orderingfood.model.DeliveryModel;
import com.example.orderingfood.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/deliver")
@PreAuthorize("hasAnyAuthority('DELIVER')")
public class DeliverController {
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private FeedbackRepo feedbackRepo;
    @Autowired
    private DeliveryRepo deliveryRepo;
    @Autowired
    private StatusRepo statusRepo;

    @GetMapping("/delivery")
    public String listDeliver(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = "null";
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        UserModel userModel = userRepo.findByLogin(username);
        Iterable<DeliveryModel> deliveryModels = deliveryRepo.findByDeliverAndOrderdelStatusName(userModel, "Подтвержден");
        model.addAttribute("delivers", deliveryModels);
        return "deliver/delivery/all";
    }

    @GetMapping("/delivery_history")
    public String listDeliverHistory(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = "null";
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        UserModel userModel = userRepo.findByLogin(username);
        Iterable<DeliveryModel> deliveryModels = deliveryRepo.findByDeliverAndOrderdelStatusName(userModel, "Выполнен");
        model.addAttribute("delivers", deliveryModels);
        return "deliver/delivery_history/all";
    }

    @GetMapping("/delivery/confirm/{id}")
    public String confirm(@PathVariable("id") long id,  Model model) {
        DeliveryModel deliveryModel = deliveryRepo.findById(id).orElse(null);
        deliveryModel.getOrderdel().setStatus(statusRepo.findByName("Выполнен"));
        deliveryRepo.save(deliveryModel);
        return "redirect:deliver/delivery";
    }
}