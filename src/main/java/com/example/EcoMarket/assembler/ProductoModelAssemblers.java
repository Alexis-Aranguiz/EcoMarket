package com.example.EcoMarket.assembler;

import com.example.EcoMarket.controller.ProductoControllerV2;
import com.example.EcoMarket.model.Producto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class ProductoModelAssemblers implements RepresentationModelAssembler<Producto, EntityModel<Producto>> {

    @Override
    public EntityModel<Producto> toModel(Producto producto) {
        return EntityModel.of(producto,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
                                .methodOn(ProductoControllerV2.class)
                                .buscar(producto.getId()))
                        .withSelfRel()
                        .withType("GET"),

                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
                                .methodOn(ProductoControllerV2.class)
                                .listar())
                        .withRel("productos")
                        .withType("GET"),

                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
                                .methodOn(ProductoControllerV2.class)
                                .actualizar(producto.getId(), producto))
                        .withRel("actualizar")
                        .withType("PUT"),

                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
                                .methodOn(ProductoControllerV2.class)
                                .eliminar(producto.getId()))
                        .withRel("eliminar")
                        .withType("DELETE"),

                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
                                .methodOn(ProductoControllerV2.class)
                                .guardar(producto))
                        .withRel("crear")
                        .withType("POST"),

                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
                                .methodOn(ProductoControllerV2.class)
                                .buscarPorNombre(producto.getNombreProducto()))
                        .withRel("buscarPorNombre")
                        .withType("GET"),

                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
                                .methodOn(ProductoControllerV2.class)
                                .buscarPorCategoria(producto.getCategoria()))
                        .withRel("buscarPorCategoria")
                        .withType("GET"),

                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
                                .methodOn(ProductoControllerV2.class)
                                .buscarPorPrecio(producto.getPrecio()))
                        .withRel("buscarPorPrecio")
                        .withType("GET")
        );
    }
}

