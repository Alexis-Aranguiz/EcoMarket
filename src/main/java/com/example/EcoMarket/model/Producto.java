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

        @Column(name = "nombreProducto", nullable = false, length = 50)
        private String nombreProducto;


    @Column(unique=false, length = 100,nullable = false)
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
