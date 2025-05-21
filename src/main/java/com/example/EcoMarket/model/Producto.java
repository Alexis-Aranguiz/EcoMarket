package com.example.EcoMarket.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Producto {
    @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @Column(unique=true, length = 30,nullable = false)
        private String nombre_producto;

        @Column(unique=true, length = 40,nullable = false)
        private String descripcion;

        @Column(unique=true, length = 40,nullable = false)
        private String categoria;

        @Column(unique=true, length = 20,nullable = false)
        private int cantidad;

        @Column(unique=true, length = 15,nullable = false)
        private double precio;

        @Column(unique=true, length = 15,nullable = false)
        private int stock;
}
