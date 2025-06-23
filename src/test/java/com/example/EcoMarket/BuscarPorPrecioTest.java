package com.example.EcoMarket;

import com.example.EcoMarket.controller.ProductoController;
import com.example.EcoMarket.model.Producto;
import com.example.EcoMarket.repository.ProductoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BuscarPorPrecioTest {

    @InjectMocks
    private ProductoController productoController;

    @Mock
    private ProductoRepository productoRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testBuscarPorPrecio() {
        double precio = 1990.0;

        Producto p1 = new Producto();
        p1.setId(1);
        p1.setNombreProducto("Yogurt");
        p1.setPrecio(precio);

        Producto p2 = new Producto();
        p2.setId(2);
        p2.setNombreProducto("Jugo");
        p2.setPrecio(precio);

        when(productoRepository.findByPrecio(precio)).thenReturn(List.of(p1, p2));

        List<Producto> resultado = productoController.buscarPorPrecio(precio);

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Yogurt", resultado.get(0).getNombreProducto());
    }
}
