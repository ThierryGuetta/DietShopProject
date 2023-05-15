package com.example.springsecurityapplication.controllers;

import com.example.springsecurityapplication.services.OrderService;
import com.example.springsecurityapplication.services.UserService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    private final OrderService orderService;
    private final UserService userService;

    public AdminController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @GetMapping(value = "/orders")
    public String orders(@RequestParam(value = "search", required = false) Integer search, @NotNull Model model) {
        if (search == null) {
            model.addAttribute("orders", orderService.findAll(search));
        } else {
            model.addAttribute("orders", orderService.findByIdEndingWith(search));
        }

        return "admin/orders";
    }

    @GetMapping(value = "/orders/{id}")
    public String viewOrder(@PathVariable(value = "id") int id, @NotNull Model model) {
        model.addAttribute("order", orderService.findById(id));

        return "admin/order";
    }

    @GetMapping(value = "/lk")
    public String lk() {
        return "index/index";
    }

    @GetMapping(value = "/allusers")
    public String allusers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin/allusers";
    }

    @PatchMapping(value = "/orders/{id}")
    public String editStatus(@PathVariable(value = "id") int id, @ModelAttribute(value = "status") String status) {
        orderService.changeStatus(id, status);

        return "redirect:/admin/orders";
    }
}
