package com.example.EcoMarket.controller;

import com.example.EcoMarket.model.Producto;
import com.example.EcoMarket.repository.ProductoRepository;
import com.example.EcoMarket.services.ProductoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/productos")

public class ProductoController {
    @Autowired
    private ProductoServices productoServices;

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping
    public ResponseEntity<List<Producto>> listar() {
        List<Producto> productos = productoServices.findAll();
        if (productos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productos);

    }
    @PostMapping
    public ResponseEntity<Producto> guardar(@RequestBody Producto producto) {
        Producto productoNuevo = productoServices.save(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productoNuevo);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> buscar(@PathVariable Integer id){
        try{
            Producto producto = productoServices.findbyId(id);
            return ResponseEntity.ok(producto);

        }catch (Exception e){

            return ResponseEntity.notFound().build();
        }


    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizar(@PathVariable Integer id, @RequestBody Producto producto) {
        try{
            Producto pro = productoServices.findbyId(id);
            pro.setId(id);
            pro.setNombreProducto(producto.getNombreProducto());
            pro.setDescripcion(producto.getDescripcion());
            pro.setCategoria(producto.getCategoria());
            pro.setCantidad(producto.getCantidad());
            pro.setPrecio(producto.getPrecio());
            pro.setStock(producto.getStock());
            productoServices.save(pro);
            return ResponseEntity.ok(pro);


        } catch (Exception e) {
            return ResponseEntity.notFound().build();

        }


    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Producto> eliminar(@PathVariable Integer id) {
        try{
            productoServices.delete(id);
            return ResponseEntity.noContent().build();

        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    //Metodos de los filtros
    @GetMapping("/buscarPorNombre")
    public List<Producto> buscarPorNombre(@RequestParam String nombreProducto){
        return productoRepository.findByNombreProducto(nombreProducto);
    }
    @GetMapping("/buscarPorCategoria")
    public List<Producto> buscarPorCategoria(@RequestParam String categoria){
        return productoRepository.findByCategoria(categoria);
    }
    @GetMapping("/buscarPorPrecio")
    public List<Producto> buscarPorPrecio(@RequestParam double precio) {
        return productoRepository.findByPrecio(precio);
    }





}
