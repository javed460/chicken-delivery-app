package com.chickendelivery.controller;

import com.chickendelivery.dto.OrderDto;
import com.chickendelivery.model.Order;
import com.chickendelivery.model.User;
import com.chickendelivery.service.OrderService;
import com.chickendelivery.service.UserService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    private final OrderService orderService;
    private final UserService userService;

    public UserController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @GetMapping("/dashboard")
    public String userDashboard(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Optional<User> userOpt = userService.findByUsername(username);

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            model.addAttribute("orders", orderService.getUserOrders(user));
            return "user/dashboard";
        }

        return "redirect:/login";
    }

    @GetMapping("/order")
    public String showOrderForm(Model model) {
        model.addAttribute("order", new OrderDto());
        return "user/order";
    }

    @PostMapping("/order")
    public String placeOrder(@Valid @ModelAttribute("order") OrderDto orderDto,
                             BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "user/order";
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Optional<User> userOpt = userService.findByUsername(username);

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            Order order = new Order();
            order.setUser(user);
            order.setDeliveryAddress(orderDto.getDeliveryAddress());
            orderService.placeOrder(order);

            return "redirect:/user/dashboard?ordered";
        }

        return "redirect:/login";
    }
}