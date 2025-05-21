package com.example.EcoMarket.repository;
import com.example.EcoMarket.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    @Query("SELECT p FROM Producto p WHERE p.nombre_producto=:nombre_producto")
    List<Producto> findByNombre_producto(String nombre_producto);

    @Query(value="SELECT * FROM Producto WHERE id=: id", nativeQuery = true)
    Producto findById_producto(@Param("id")int id);



}