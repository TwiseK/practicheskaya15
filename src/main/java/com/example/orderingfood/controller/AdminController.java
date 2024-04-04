package com.example.orderingfood.controller;

import com.example.orderingfood.Repo.FeedbackRepo;
import com.example.orderingfood.Repo.StatusRepo;
import com.example.orderingfood.Repo.TypeDelRepo;
import com.example.orderingfood.Repo.UserRepo;
import com.example.orderingfood.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class AdminController {
    @Autowired
    public StatusRepo statusRepo;
    @Autowired
    public TypeDelRepo typeDelRepo;
    @Autowired
    public UserRepo userRepo;
    @Autowired
    public FeedbackRepo feedbackRepo;
    Logger logger = LoggerFactory.getLogger(AdminController.class);


    ///STATUS

    @GetMapping("/admin_status")
    public String listAdminStatus(Model model) {
        Iterable<StatusModel> statusModels = statusRepo.findAll();
        model.addAttribute("statuss", statusModels);
        logger.info("INFO message logs");
        logger.warn("WARN message logs");
        logger.error("ERROR message logs");
        return "admin/admin_status/all";
    }

    @GetMapping("/admin_status/create")
    public String showAddStatus(Model model) {
        StatusModel statusModel = new StatusModel();
        model.addAttribute("statuss", statusModel);
        return "/admin/admin_status/create";
    }

    @PostMapping("/admin_status/create")
    public String addStatus(
            @ModelAttribute("status") StatusModel statusModel,
            BindingResult bindingResult) {

        statusRepo.save(statusModel);

        return "redirect:/admin/admin_status";
    }

    @GetMapping("/admin_status/update/{id}")
    public String showEditStatus(@PathVariable("id") long id, Model model) {
        StatusModel statusModel = statusRepo.findById(id).orElse(null);
        if (statusModel == null) {
            return "redirect:/admin/admin_status/update";
        }

        model.addAttribute("statuss", statusModel);
        return "/admin/admin_status/update";
    }

    @PostMapping("/admin_status/update/{id}")
    public String editStatus1(
            @PathVariable("id") long id,
            @Valid @ModelAttribute("statuss") StatusModel statusModel,
            BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {

            return "/admin/admin_status/update";
        }
        statusModel.setId(id);

        statusRepo.save(statusModel);
        return "redirect:/admin/admin_status";
    }

    @GetMapping("/admin_status/delete/{id}")
    public String deleteStatus(@PathVariable("id") long id) {
        statusRepo.deleteById(id);
        return "redirect:/admin/admin_status";
    }
    ///USER

    @GetMapping("/admin_user")
    public String listAdminUser(Model model) {
        Iterable<UserModel> userModels = userRepo.findAll();
        model.addAttribute("users", userModels);
        return "admin/admin_user/all";
    }

    /*@GetMapping("/admin_user/create")
    public String showAddUser(Model model) {
        UserModel userModel = new UserModel();
        model.addAttribute("users", userModel);
        return "/admin/admin_user/create";
    }

    @PostMapping("/admin_user/create")
    public String addUser(
            @ModelAttribute("user") UserModel userModel,
            BindingResult bindingResult) {

        userRepo.save(userModel);

        return "redirect:/admin/admin_user";
    }*/

    @GetMapping("/admin_user/update/{id}")
    public String showEditUser(@PathVariable("id") long id, Model model) {
        UserModel userModel = userRepo.findById(id).orElse(null);
        if (userModel == null) {
            return "redirect:/admin/admin_user/update";
        }
        model.addAttribute("roles", roleEnum.values());
        model.addAttribute("users", userModel);
        return "/admin/admin_user/update";
    }
    @PostMapping("/admin_user/update/{id}")
    public String editUser(
            @RequestParam(name="roles[]", required = false) String[] roles,
            @PathVariable("id") long id,
            @Valid @ModelAttribute("users") UserModel userModel,
            BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {

            return "/admin/admin_user/update";
        }
        userModel.getRoles().clear();
        if(roles != null)
        {
            for(String role: roles)
            {
                userModel.getRoles().add(roleEnum.valueOf(role));
            }
        }
        userModel.setActive(true);
        userModel.setId(id);
        userRepo.save(userModel);
        return "redirect:/admin/admin_user";
    }

    @GetMapping("/admin_user/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userRepo.deleteById(id);
        return "redirect:/admin/admin_user";
    }

    ///FEEDBACK

    @GetMapping("/admin_feedback")
    public String listAdminFeedback(Model model) {
        Iterable<FeedbackModel> feedbackModels = feedbackRepo.findAll();
        model.addAttribute("feedbacks", feedbackModels);
        return "admin/admin_feedback/all";
    }

    @GetMapping("/admin_feedback/delete/{id}")
    public String deleteFeedback(@PathVariable("id") long id) {
        feedbackRepo.deleteById(id);
        return "redirect:/admin/admin_feedback";
    }

    ///TYPEDEL

    @GetMapping("/admin_typedel")
    public String listAdminTypedel(Model model) {
        Iterable<TypeDelModel> typeDelModels = typeDelRepo.findAll();
        model.addAttribute("typedels", typeDelModels);
        return "admin/admin_typedel/all";
    }

    @GetMapping("/admin_typedel/create")
    public String showAddTypedel(Model model) {
        TypeDelModel typeDelModel = new TypeDelModel();
        model.addAttribute("typedels", typeDelModel);
        return "/admin/admin_typedel/create";
    }

    @PostMapping("/admin_typedel/create")
    public String addTypedel(
            @ModelAttribute("typedel") TypeDelModel typeDelModel,
            BindingResult bindingResult) {

        typeDelRepo.save(typeDelModel);

        return "redirect:/admin/admin_typedel";
    }

    @GetMapping("/admin_typedel/update/{id}")
    public String showEditTypedel(@PathVariable("id") long id, Model model) {
        TypeDelModel typeDelModel = typeDelRepo.findById(id).orElse(null);
        if (typeDelModel == null) {
            return "redirect:/admin/admin_typedel/update";
        }

        model.addAttribute("typedels", typeDelModel);
        return "/admin/admin_typedel/update";
    }

    @PostMapping("/admin_typedel/update/{id}")
    public String editTypedel(
            @PathVariable("id") long id,
            @Valid @ModelAttribute("typedels") TypeDelModel typeDelModel,
            BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {

            return "/admin/admin_typedel/update";
        }
        typeDelModel.setId(id);

        typeDelRepo.save(typeDelModel);
        return "redirect:/admin/admin_typedel";
    }

    @GetMapping("/admin_typedel/delete/{id}")
    public String deleteTypedel(@PathVariable("id") long id) {
        typeDelRepo.deleteById(id);
        return "redirect:/admin/admin_typedel";
    }
}
