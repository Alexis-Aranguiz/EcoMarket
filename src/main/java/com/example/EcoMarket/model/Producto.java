package com.example.EcoMarket.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Producto {
    private String nombre_producto;
    private String descripcion;
    private String categoria;
    private int cantidad;
    private double precio;
    private int stock;
}
