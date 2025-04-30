package com.virtualcoffee.pedidosapi.service;

import com.virtualcoffee.pedidosapi.model.Order;

import jakarta.validation.Valid;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {

    private final List<Order> orders = new ArrayList<>();
    private final BeverageClient beverageClient;

    // Inyectamos la dependencia de BeverageClient
    public OrderService(BeverageClient beverageClient) {
        this.beverageClient = beverageClient;
    }

    //añadir aña
    public Order addOrder(Order order) {
        // Validamos si la bebida existe
        if (!beverageClient.isBeverageAvailable(order.getName())) {
            throw new IllegalArgumentException("Bebida no disponible");
        }

        order.setId(UUID.randomUUID().toString());
        order.setStatus("pending");
        orders.add(order);
        return order;
    }

    public List<Order> listOrders() {
        return orders;
    }

    public Order updateOrder(String id, Order updatedOrder) {
        Optional<Order> optionalOrder = orders.stream()
                .filter(order -> order.getId().equals(id))
                .findFirst();

        if (optionalOrder.isPresent()) {
            Order existingOrder = optionalOrder.get();
            existingOrder.setName(updatedOrder.getName());
            existingOrder.setSize(updatedOrder.getSize());
            existingOrder.setStatus(updatedOrder.getStatus());
            return existingOrder;
        } else {
            throw new RuntimeException("Order not found with id: " + id);
        }
    }
}