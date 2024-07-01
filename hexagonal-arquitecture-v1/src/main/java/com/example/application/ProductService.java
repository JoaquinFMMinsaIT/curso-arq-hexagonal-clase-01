package com.example.application;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProductService {
    public String getProducts() {
        // Implementación de obtención de productos
        return "Listado de productos 01-07-2024";
    }
}
