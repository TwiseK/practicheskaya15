package com.example.orderingfood.controller;

import com.example.orderingfood.Repo.UserRepo;
import com.example.orderingfood.model.UserModel;
import com.example.orderingfood.model.roleEnum;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
public class RegistrationController {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/access_denied")
    private String access()
    {
        return "/access_denied";
    }
    @Autowired
    private UserRepo userRepo;
    @GetMapping("/registration")
    private String RegView()
    {
        return "regis";
    }
    @PostMapping("/registration")
    private String Reg(UserModel userModel, Model model)
    {
        UserModel user_from_db = userRepo.findByLogin(userModel.getLogin());
        if (user_from_db != null)
        {
            model.addAttribute("message", "Пользователь с таким логином уже существует");
            return "regis";
        }
        userModel.setActive(true);
        userModel.setRoles(Collections.singleton(roleEnum.USER));
        userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
        userRepo.save(userModel);
        return "/login";
    }
}