package com.virtualcoffee.pedidosapi.service;

import com.virtualcoffee.pedidosapi.model.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderServiceTest {

    @Mock
    private BeverageClient beverageClient;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateOrderSuccessfully() {
        Order order = new Order();
        order.setName("Café Americano");
        order.setSize("medium");

        when(beverageClient.isBeverageAvailable("Café Americano")).thenReturn(true);

        Order createdOrder = orderService.addOrder(order);

        assertNotNull(createdOrder.getId());
        assertEquals("Café Americano", createdOrder.getName());
        assertEquals("medium", createdOrder.getSize());
        assertEquals("pending", createdOrder.getStatus());
    }

    @Test
    void shouldRejectOrderIfBeverageUnavailable() {
        Order order = new Order();
        order.setName("Bebida Desconocida");
        order.setSize("small");

        when(beverageClient.isBeverageAvailable("Bebida Desconocida")).thenReturn(false);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            orderService.addOrder(order);
        });

        assertEquals("Bebida no disponible", exception.getMessage());
    }

    @Test
    void shouldUpdateOrderSuccessfully() {
        when(beverageClient.isBeverageAvailable(anyString())).thenReturn(true);

        Order order = new Order();
        order.setName("Café Americano");
        order.setSize("medium");

        Order createdOrder = orderService.addOrder(order);

        Order updatedData = new Order();
        updatedData.setName("Café Americano");
        updatedData.setSize("medium");
        updatedData.setStatus("completed");

        Order updatedOrder = orderService.updateOrder(createdOrder.getId(), updatedData);

        assertEquals("completed", updatedOrder.getStatus());
    }
}
