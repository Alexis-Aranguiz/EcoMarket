package com.example.EcoMarket.controller;

import com.example.EcoMarket.assembler.ProductoModelAssemblers;
import com.example.EcoMarket.model.Producto;
import com.example.EcoMarket.repository.ProductoRepository;
import com.example.EcoMarket.services.ProductoServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Producto API con HATEOAS", description = "Operaciones relacionadas con productos")
@RestController
@RequestMapping("/api/v2/productos")
public class ProductoControllerV2 {

    @Autowired
    private ProductoServices productoServices;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ProductoModelAssemblers assembler;

    @Operation(summary = "Listar todos los productos")
    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Producto>>> listar() {
        List<Producto> productos = productoServices.findAll();
        if (productos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<EntityModel<Producto>> productosModel = productos.stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(CollectionModel.of(productosModel,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProductoControllerV2.class).listar()).withSelfRel()));
    }

    @Operation(summary = "Guardar un nuevo producto")
    @PostMapping
    public ResponseEntity<EntityModel<Producto>> guardar(@RequestBody Producto producto) {
        Producto productoNuevo = productoServices.save(producto);
        EntityModel<Producto> entityModel = assembler.toModel(productoNuevo);
        return ResponseEntity.status(HttpStatus.CREATED).body(entityModel);
    }

    @Operation(summary = "Buscar producto por ID")
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Producto>> buscar(
            @Parameter(description = "ID del producto a buscar") @PathVariable Integer id) {
        try {
            Producto producto = productoServices.findbyId(id);
            return ResponseEntity.ok(assembler.toModel(producto));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Actualizar un producto")
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Producto>> actualizar(
            @Parameter(description = "ID del producto a actualizar") @PathVariable Integer id,
            @RequestBody Producto producto) {
        try {
            Producto pro = productoServices.findbyId(id);
            pro.setId(id);
            pro.setNombreProducto(producto.getNombreProducto());
            pro.setDescripcion(producto.getDescripcion());
            pro.setCategoria(producto.getCategoria());
            pro.setCantidad(producto.getCantidad());
            pro.setPrecio(producto.getPrecio());
            pro.setStock(producto.getStock());
            productoServices.save(pro);
            return ResponseEntity.ok(assembler.toModel(pro));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Eliminar un producto")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @Parameter(description = "ID del producto a eliminar") @PathVariable Integer id) {
        try {
            productoServices.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Buscar productos por nombre")
    @GetMapping("/buscarPorNombre")
    public ResponseEntity<CollectionModel<EntityModel<Producto>>> buscarPorNombre(
            @Parameter(description = "Nombre del producto") @RequestParam String nombreProducto) {
        List<Producto> productos = productoRepository.findByNombreProducto(nombreProducto);
        if (productos.isEmpty()) return ResponseEntity.notFound().build();
        List<EntityModel<Producto>> productosModel = productos.stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(CollectionModel.of(productosModel));
    }

    @Operation(summary = "Buscar productos por categoría")
    @GetMapping("/buscarPorCategoria")
    public ResponseEntity<CollectionModel<EntityModel<Producto>>> buscarPorCategoria(
            @Parameter(description = "Categoría del producto") @RequestParam String categoria) {
        List<Producto> productos = productoRepository.findByCategoria(categoria);
        if (productos.isEmpty()) return ResponseEntity.notFound().build();
        List<EntityModel<Producto>> productosModel = productos.stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(CollectionModel.of(productosModel));
    }

    @Operation(summary = "Buscar productos por precio")
    @GetMapping("/buscarPorPrecio")
    public ResponseEntity<CollectionModel<EntityModel<Producto>>> buscarPorPrecio(
            @Parameter(description = "Precio del producto") @RequestParam double precio) {
        List<Producto> productos = productoRepository.findByPrecio(precio);
        if (productos.isEmpty()) return ResponseEntity.notFound().build();
        List<EntityModel<Producto>> productosModel = productos.stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(CollectionModel.of(productosModel));
    }
}
