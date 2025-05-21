package com.example.EcoMarket.services;

import com.example.EcoMarket.model.Producto;
import com.example.EcoMarket.repository.ProductoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional

public class ProductoServices {
    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> findAll() {return productoRepository.findAll;}

    public Producto findbyId(long id){return productoRepository.findById(id).get();}

    public Producto save(Producto producto) {return productoRepository.save(producto);}

    public void delete(long id) {productoRepository.deleteById(id);}


}
