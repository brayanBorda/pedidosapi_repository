package com.virtualcoffee.pedidosapi.controller;

import com.virtualcoffee.pedidosapi.model.Order;
import com.virtualcoffee.pedidosapi.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public Order createOrder(@Valid @RequestBody Order order) {
        return orderService.addOrder(order);
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.listOrders();
    }

    @PutMapping("/{id}")
    public Order updateOrder(@PathVariable String id, @Valid @RequestBody Order updatedOrder) {
        return orderService.updateOrder(id, updatedOrder);
    }

}
