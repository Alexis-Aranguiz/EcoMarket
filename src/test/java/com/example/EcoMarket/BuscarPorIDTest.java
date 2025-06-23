package com.example.EcoMarket;

import com.example.EcoMarket.model.Producto;
import com.example.EcoMarket.repository.ProductoRepository;
import com.example.EcoMarket.services.ProductoServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BuscarPorIDTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoServices productoServices;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        Producto p1 = new Producto();
        Producto p2 = new Producto();
        when(productoRepository.findAll()).thenReturn(Arrays.asList(p1, p2));

        List<Producto> productos = productoServices.findAll();
        assertEquals(2, productos.size());
    }

    @Test
    void testFindById() {
        Producto producto = new Producto();
        producto.setId(1);
        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));

        Producto resultado = productoServices.findbyId(1);
        assertEquals(1, resultado.getId());
    }

    @Test
    void testSave() {
        Producto producto = new Producto();
        producto.setNombreProducto("Mouse Gamer");
        when(productoRepository.save(producto)).thenReturn(producto);

        Producto guardado = productoServices.save(producto);
        assertEquals("Mouse Gamer", guardado.getNombreProducto());
    }

    @Test
    void testDelete() {
        doNothing().when(productoRepository).deleteById(1L);

        productoServices.delete(1);
        verify(productoRepository, times(1)).deleteById(1L);
    }
}
