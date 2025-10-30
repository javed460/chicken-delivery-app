package com.chickendelivery.controller;

import com.chickendelivery.model.Order;
import com.chickendelivery.model.OrderStatus;
import com.chickendelivery.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final OrderService orderService;

    public AdminController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/dashboard")
    public String adminDashboard(@RequestParam(required = false) OrderStatus status, Model model) {
        List<Order> orders;

        if (status != null) {
            orders = orderService.getOrdersByStatus(status);
        } else {
            orders = orderService.getAllOrders();
        }

        model.addAttribute("orders", orders);
        model.addAttribute("statuses", OrderStatus.values());
        model.addAttribute("selectedStatus", status);

        return "admin/dashboard";
    }

    @PostMapping("/orders/update-status")
    public String updateOrderStatus(@RequestParam Long orderId,
                                    @RequestParam OrderStatus status) {
        orderService.updateOrderStatus(orderId, status);
        return "redirect:/admin/dashboard";
    }
}