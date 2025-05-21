package com.example.EcoMarket.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table (name="Producto")
@AllArgsConstructor
@NoArgsConstructor

public class Producto {
    @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @Column(unique=false, length = 30,nullable = false)
        private String nombre_producto;

        @Column(unique=false, length = 40,nullable = false)
        private String descripcion;

        @Column(unique=false, length = 40,nullable = false)
        private String categoria;

        @Column(unique=false, length = 20,nullable = false)
        private int cantidad;

        @Column(unique=false, length = 15,nullable = false)
        private double precio;

        @Column(unique=false, length = 15,nullable = false)
        private int stock;
}
