package com.example.domain.service;

import com.example.domain.model.Order;
import com.example.domain.model.OrderItem;
import com.google.errorprone.annotations.RestrictedApi;
import io.quarkus.test.QuarKusTest;
import jdk.incubator.vector.VectorOperators;
import org.junit.jupiter.api.Test;
import jakarta.inject.Inject;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static io.smallrye.common.constraint.Assert.assertNotNull;
import static java.time.LocalDateTime.*;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class OrderServiceTest {

    @Inject
    OrderService orderService;

    @Test
    public  void testCreateOrder(){
        Order order = new Order(LocalDateTime.now(), "PENDING");
        OrderService.createorder(order);
        assertNotNull(order.getId());
    }

    @Test
    public void testAddItemOrderToOrder(){
        Order order = new Order(now(), "PENDING");
        orderService.createOrder(order);
        OrderItem item =new OrderItem("product1", 2, new BigDecimal("50.0"));
        OrderService.addItemToOrder(order.getId(), item);
    }

    @Test public void testUpdateOrderStatus() {
        Order order = new Order(LocalDateTime.now(), "PENDING");
        orderService.createOrder(order);
        orderService.updateOrderStatus(order.getId(), "CONFIRMED");
        assertEquals("CONFIRMED", orderService.findOrderById(order.getId()).getStatus());
    }
}
