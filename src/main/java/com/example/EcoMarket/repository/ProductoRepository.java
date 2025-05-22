package com.example.EcoMarket.repository;
import com.example.EcoMarket.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    //Filtros
    List<Producto> findByNombreProducto(String nombreProducto);
    List<Producto> findByCategoria(String categoria);

    @Query(value="SELECT * FROM Producto WHERE id=: id", nativeQuery = true)
    Producto findById_producto(@Param("id")int id);



}