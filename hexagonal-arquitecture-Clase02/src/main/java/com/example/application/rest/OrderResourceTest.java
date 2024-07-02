package com.example.application.rest;

import com.example.domain.model.Order;
import com.example.domain.model.OrderItem;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class OrderResourceTest {

    @Test
    public void testAddItemToOrderEndpoint(){
        Order order = new Order(LocalDateTime.now(), "PENDING");
        given()
                .contenType("application/json")
                .body(order)
                .when().post("/orders/{orderid}/items", order.getId())
                .then()
                .statusCode(201);

        OrderItem item = new OrderItem("product1", 2, new BigDecimal("50.0"));
        given()
                .contenType("application/json")
                .body(item)
                .when().post("/orders/{orderid}/items", order.getId())
                .then()
                .statusCode(200);

    }

    @Test
    public void testUpdateOrderStatusEndpoint(){
        Order order = new Order(LocalDateTime.now(), "PENDING");
        given()
                .contenType("application/json")
                .body(order)
                .when().post("/orders", order.getId())
                .then()
                .statusCode(201);

        given()
                .contenType("application/json")
                .body("CPNFIRMED")
                .when().put("/orders/{orderid}/status", order.getId())
                .then()
                .statusCode(200)
                .body("status", is("CONFIRMED"));

    }

}
