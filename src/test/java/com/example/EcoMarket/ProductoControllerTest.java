package com.example.EcoMarket;
import com.example.EcoMarket.controller.ProductoController;
import com.example.EcoMarket.model.Producto;
import com.example.EcoMarket.repository.ProductoRepository;
import com.example.EcoMarket.services.ProductoServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductoController.class)
class ProductoControllerTest {
    @MockBean
    private ProductoRepository productoRepository;
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductoServices productoServices;

    @Autowired
    private ObjectMapper objectMapper;

    private Producto crearProducto() {
        Producto p = new Producto();
        p.setId(1);
        p.setNombre_producto("Aceite");
        p.setDescripcion("Aceite vegetal");
        p.setCategoria("Alimentos");
        p.setCantidad(5);
        p.setPrecio(3.99);
        p.setStock(50);
        return p;
    }

    @Test
    void testListarProductos() throws Exception {
        Mockito.when(productoServices.findAll()).thenReturn(Arrays.asList(crearProducto()));
        mockMvc.perform(get("/api/v1/productos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    void testGuardarProducto() throws Exception {
        Producto producto = crearProducto();
        Mockito.when(productoServices.save(any(Producto.class))).thenReturn(producto);

        mockMvc.perform(post("/api/v1/productos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(producto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre_producto").value("Aceite"));
    }

    @Test
    void testBuscarPorId_Existente() throws Exception {
        Mockito.when(productoServices.findbyId(1)).thenReturn(crearProducto());

        mockMvc.perform(get("/api/v1/productos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void testBuscarPorId_NoExistente() throws Exception {
        Mockito.when(productoServices.findbyId(99)).thenThrow(new RuntimeException());

        mockMvc.perform(get("/api/v1/productos/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testEliminarProducto() throws Exception {
        Mockito.doNothing().when(productoServices).delete(1L);

        mockMvc.perform(delete("/api/v1/productos/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testActualizarProducto() throws Exception {
        Producto original = crearProducto();
        Producto actualizado = crearProducto();
        actualizado.setPrecio(5.99);

        Mockito.when(productoServices.findbyId(1)).thenReturn(original);
        Mockito.when(productoServices.save(any(Producto.class))).thenReturn(actualizado);

        mockMvc.perform(put("/api/v1/productos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(actualizado)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.precio").value(5.99));
    }
    @Test
    void testBuscarPorNombre() throws Exception {
        Producto producto = crearProducto();
        Mockito.when(productoRepository.findByNombre_producto("Aceite"))
                .thenReturn(Arrays.asList(producto));

        mockMvc.perform(get("/api/v1/productos/buscarPorNombre")
                        .param("nombre_producto", "Aceite"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].nombre_producto").value("Aceite"));
    }

    @Test
    void testBuscarPorCategoria() throws Exception {
        Producto producto = crearProducto();
        Mockito.when(productoRepository.findByCategoria("Alimentos"))
                .thenReturn(Arrays.asList(producto));

        mockMvc.perform(get("/api/v1/productos/buscarPorCategoria")
                        .param("categoria", "Alimentos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].categoria").value("Alimentos"));
    }

}
