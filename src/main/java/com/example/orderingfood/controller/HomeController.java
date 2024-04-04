package com.example.orderingfood.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
@PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER', 'DELIVER', 'USER')")
public class HomeController {

    @GetMapping()
    public String Home(Model model) {
        return "/home";
    }
}
