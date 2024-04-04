package com.example.orderingfood.controller;

import com.example.orderingfood.Repo.*;
import com.example.orderingfood.model.*;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping("/manager")
@PreAuthorize("hasAnyAuthority('MANAGER')")
public class ManagerController {
    @Autowired
    private DishRepo dishRepo;
    @Autowired
    private TypeDishRepo typeDishRepo;
    @Autowired
    private DeliveryRepo deliveryRepo;
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private StatusRepo statusRepo;


    @GetMapping("/manager_dish")
    public String listManager(Model model) {
        Iterable<DishModel> dishModels = dishRepo.findAll();
        model.addAttribute("dishes", dishModels);
        return "manager/manager_dish/all";
    }

    @GetMapping("/manager_typedish")
    public String listManagerTypeDish(Model model) {
        Iterable<TypeDishModel> typeDishModels = typeDishRepo.findAll();
        model.addAttribute("typedishes", typeDishModels);
        return "manager/manager_typedish/all";
    }


    @GetMapping("/manager_history")
    public String listManagerHistory(Model model) {
        Iterable<DeliveryModel> deliveryModels = deliveryRepo.findByOrderdelStatusName("Выполнен");
        model.addAttribute("orders", deliveryModels);
        return "manager/manager_history/all";
    }

    @GetMapping("/manager_zakaz")
    public String listManagerZakaz(Model model) {
        Iterable<OrderModel> orderModels = orderRepo.findByStatusName("Ожидают подтверждения");
        model.addAttribute("orders", orderModels);
        return "manager/manager_zakaz/all";
    }

    @GetMapping("/manager_cancel")
    public String listManagerCancel(Model model) {
        Iterable<OrderModel> orderModels = orderRepo.findByStatusName("Отказано");
        model.addAttribute("orders", orderModels);
        return "manager/manager_cancel/all";
    }



    @GetMapping("/manager_dish/create")
    public String showAddDish(Model model) {
        DishModel dishModel = new DishModel();
        model.addAttribute("dishes", dishModel);
        Iterable<TypeDishModel> typeDishModels = typeDishRepo.findAll();
        model.addAttribute("typedishes", typeDishModels);
        return "/manager/manager_dish/create";
    }

    @PostMapping("/manager_dish/create")
    public String add2(
            @ModelAttribute("dish") DishModel dishModel,
            BindingResult bindingResult) {

        dishRepo.save(dishModel);

        return "redirect:/manager/manager_dish";
    }

    @GetMapping("/manager_zakaz/create/{id}")
    public String showAddZakaz(@PathVariable("id") long id, Model model) {
        OrderModel orderModel = orderRepo.findById(id).orElse(null);
        orderModel.setStatus(statusRepo.findByName("Подтвержден"));
        orderRepo.save(orderModel);
        DeliveryModel deliveryModel = new DeliveryModel();
        deliveryModel.setUser(orderModel.getBasket().getBasket());
        deliveryModel.setOrderdel(orderModel);
        Iterable<UserModel> userModels = userRepo.findByPost("Доставщик");
        model.addAttribute("delivers", userModels);
        model.addAttribute("delivery", deliveryModel);
        return "/manager/manager_zakaz/create";
    }

    @GetMapping("/manager_dish/update/{id}")
    public String showEditForm1(@PathVariable("id") long id, Model model) {
        DishModel dishModel = dishRepo.findById(id).orElse(null);
        if (dishModel == null) {
            return "redirect:/manager/manager_dish/update";
        }
        model.addAttribute("dishes", dishModel);
        return "/manager/manager_dish/update";
    }

    @PostMapping("/manager_zakaz/create")
    public String create1(
            @ModelAttribute("delivery") DeliveryModel deliveryModel,
            BindingResult bindingResult) {
        try {
            deliveryRepo.save(deliveryModel);
        }
        catch (NullPointerException e) {
            return "redirect:/manager/manager_zakaz";
        }

        return "redirect:/manager/manager_zakaz";
    }

    @PostMapping("/manager_dish/update/{id}")
    public String edit1(
            @PathVariable("id") long id,
            @Valid @ModelAttribute("dishes") DishModel dishModel,
            BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {

            return "/manager/manager_dish/update";
        }
        dishModel.setId(id);

        dishRepo.save(dishModel);
        return "redirect:/manager/manager_dish";
    }

    @GetMapping("/manager_typedish/create")
    public String showAddTypeDish(Model model) {
        TypeDishModel typeDishModel = new TypeDishModel();
        model.addAttribute("typedishes", typeDishModel);
        return "/manager/manager_typedish/create";
    }

    @PostMapping("/manager_typedish/create")
    public String addType2(
            @ModelAttribute("typedish") TypeDishModel typeDishModel,
            BindingResult bindingResult) {

        typeDishRepo.save(typeDishModel);

        return "redirect:/manager/manager_typedish";
    }

    @GetMapping("/manager_typedish/update/{id}")
    public String showEditType1(@PathVariable("id") long id, Model model) {
        TypeDishModel typeDishModel = typeDishRepo.findById(id).orElse(null);
        if (typeDishModel == null) {
            return "redirect:/manager/manager_typedish/update";
        }

        model.addAttribute("typedishes", typeDishModel);
        return "/manager/manager_typedish/update";
    }

    @PostMapping("/manager_typedish/update/{id}")
    public String editType1(
            @PathVariable("id") long id,
            @Valid @ModelAttribute("typedishes") TypeDishModel typeDishModel,
            BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {

            return "/manager/manager_typedish/update";
        }
        typeDishModel.setId(id);

        typeDishRepo.save(typeDishModel);
        return "redirect:/manager/manager_typedish";
    }

    @GetMapping("/manager_dish/delete/{id}")
    public String deleteDish(@PathVariable("id") long id) {
        dishRepo.deleteById(id);
        return "redirect:/manager/manager_dish";
    }

    @GetMapping("/manager_typedish/delete/{id}")
    public String deleteTypeDish(@PathVariable("id") long id) {
        typeDishRepo.deleteById(id);
        return "redirect:/manager/manager_typedish";
    }

    @GetMapping("/manager_zakaz/search")
    public String searchZakaz(@RequestParam(name = "Ожидают подтверждения") String status, Model model) {
        Iterable<OrderModel> orderModels = orderRepo.findByStatusName("Ожидают подтверждения");
        model.addAttribute("orders", orderModels);
        return "manager/manager_zakaz/all";
    }

    @GetMapping("/manager_cancel/close/{id}")
    public String close2(@PathVariable("id") long id) {
        OrderModel orderModel = orderRepo.findById(id).orElse(null);
        StatusModel statusModels = statusRepo.findByName("Отказано");
        //поиск записи в таблице СТАТУС, где name Отказать
        orderModel.setStatus(statusModels); //Передать не текст, а модель СТАТУСА
        orderRepo.save(orderModel);
        return "redirect:/manager/manager_cancel/all";
    }


}