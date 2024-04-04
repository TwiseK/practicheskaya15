package com.example.orderingfood.controller;

import com.example.orderingfood.Repo.*;
import com.example.orderingfood.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Iterator;
import java.util.UUID;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAnyAuthority('DELIVER', 'ADMIN', 'MANAGER', 'USER')")
public class UserController {

    @Autowired
    public TypeDishRepo typeDishRepo;
    @Autowired
    public DishRepo dishRepo;
    @Autowired
    public UserRepo userRepo;
    @Autowired
    public BasketRepo basketRepo;
    @Autowired
    public SelectedDishRepo selectedDishRepo;
    @Autowired
    public StatusRepo statusRepo;
    @Autowired
    public TypeDelRepo typeDelRepo;
    @Autowired
    public OrderRepo orderRepo;

    @GetMapping("/zastavka")
    public String listZastavka() {
        return "/user/zastavka";
    }

    //Тип Меню

    @GetMapping("/user_menu/all")
    public String listUserMenu(Model model) {
        TypeDishModel typeDishModel = typeDishRepo.findByName("Завтрак");
        Iterable<DishModel> dishModels = dishRepo.findByTypedish(typeDishModel);
        model.addAttribute("dishes", dishModels);
        return "user/user_menu/all";
    }

    @GetMapping("/user_menu/breakfast")
    public String listUserMenuBreakfast(Model model) {
        TypeDishModel typeDishModel = typeDishRepo.findByName("Завтрак");
        Iterable<DishModel> dishModels = dishRepo.findByTypedish(typeDishModel);
        model.addAttribute("dishes", dishModels);
        return "user/user_menu/all";
    }

    @GetMapping("/user_menu/lunch")
    public String listUserMenuLunch(Model model) {
        TypeDishModel typeDishModel = typeDishRepo.findByName("Обед");
        Iterable<DishModel> dishModels = dishRepo.findByTypedish(typeDishModel);
        model.addAttribute("dishes", dishModels);
        return "user/user_menu/all";
    }

    @GetMapping("/user_menu/dinner")
    public String listUserMenuDinner(Model model) {
        TypeDishModel typeDishModel = typeDishRepo.findByName("Ужин");
        Iterable<DishModel> dishModels = dishRepo.findByTypedish(typeDishModel);
        model.addAttribute("dishes", dishModels);
        return "user/user_menu/all";
    }

    @GetMapping("/adddish/{id}")
    public String listUserAddBasket(@PathVariable("id") long id, Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = "null";
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        UserModel userModel = userRepo.findByLogin(username);

        BasketModel basketModel = basketRepo.findByBasketAndActive(userModel, true);
        if (basketModel == null){
            basketModel = new BasketModel();
            basketModel.setBasket(userModel);
            String uid = String.valueOf(UUID.randomUUID());
            basketModel.setNum(uid);
            basketModel.setActive(true);
            basketRepo.save(basketModel);
        }
        DishModel dishModel = dishRepo.findById(id).orElse(null);
        SelectedDishModel selectedDishModel = new SelectedDishModel();
        selectedDishModel.setBasket(basketModel);
        selectedDishModel.setDish(dishModel);
        selectedDishModel.setCost(dishModel.getCost());
        selectedDishRepo.save(selectedDishModel);


        return "redirect:/user/user_menu/all";
    }

    @GetMapping("/user_basket")
    public String listUserBasket(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = "null";
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        UserModel userModel = userRepo.findByLogin(username);
        BasketModel basketModel = basketRepo.findByBasketAndActive(userModel, true);
        Iterable <SelectedDishModel> selectedDishModels = selectedDishRepo.findByBasket(basketModel);
        model.addAttribute("selecteddishes", selectedDishModels);
        model.addAttribute("basket", basketModel);
        return "user/user_basket/all";
    }

    @GetMapping("/user_basket/delete/{id}")
    public String deleteSelectedDish(@PathVariable("id") long id) {
        selectedDishRepo.deleteById(id);
        return "redirect:/user/user_basket";
    }

    @GetMapping("/user_basket/create/{id}")
    public String showAddBasket(@PathVariable("id") long id, Model model) {
        BasketModel basketModel = basketRepo.findById(id).orElse(null);
        OrderModel orderModel = new OrderModel();
        orderModel.setCost(basketModel.getSum());
        LocalDateTime localDateTime = LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        orderModel.setDateTime(localDateTime);
        orderModel.setBasket(basketModel);
        StatusModel statusModel = statusRepo.findByName("Ожидают подтверждения");
        orderModel.setStatus(statusModel);
        TypeDelModel typeDelModel = typeDelRepo.findByName("Курьером");
        orderModel.setTypedel(typeDelModel);
        orderRepo.save(orderModel);
        basketModel.setActive(false);
        basketRepo.save(basketModel);
        return "redirect:/user/user_menu/all";
    }

    @GetMapping("/user_profile")
    public String checkProfile(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = "null";
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        UserModel userModel = userRepo.findByLogin(username);
        model.addAttribute("user", userModel);
        Iterable<OrderModel> orderModels = orderRepo.findAllByBasketBasket(userModel);
        model.addAttribute("orders", orderModels);
        return "/user/user_profile/all";
    }

    @GetMapping("/user_profile/update/{id}")
    public String showEditForm10(@PathVariable("id") long id, Model model) {
        UserModel userModel = userRepo.findById(id).orElse(null);
        if (userModel == null) {
            return "redirect:/user/user_profile/update";
        }

        model.addAttribute("users", userModel);
        return "/user/user_profile/update";
    }

    @PostMapping("/user_profile/update/{id}")
    public String edit10(
            @PathVariable("id") long id,
            @Valid @ModelAttribute("users") UserModel userModel,
            BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {

            return "/user/user_profile/update";
        }
        userModel.setRoles(Collections.singleton(roleEnum.USER));
        userModel.setActive(true);
        userModel.setId(id);
        userRepo.save(userModel);
        return "redirect:/user/user_profile";
    }
}
