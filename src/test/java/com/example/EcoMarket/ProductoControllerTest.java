package com.example.EcoMarket;

import com.example.EcoMarket.controller.ProductoController;
import com.example.EcoMarket.model.Producto;
import com.example.EcoMarket.repository.ProductoRepository;
import com.example.EcoMarket.services.ProductoServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductoControllerTest {

    @InjectMocks
    private ProductoController productoController;

    @Mock
    private ProductoRepository productoRepository;

    @Mock
    private ProductoServices productoServices;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testBuscarPorPrecio() {
        double precioBuscado = 1990.0;

        Producto producto1 = new Producto();
        producto1.setId(1);
        producto1.setNombreProducto("Yogurt");
        producto1.setPrecio(precioBuscado);

        Producto producto2 = new Producto();
        producto2.setId(2);
        producto2.setNombreProducto("Jugo");
        producto2.setPrecio(precioBuscado);

        when(productoRepository.findByPrecio(precioBuscado)).thenReturn(List.of(producto1, producto2));

        List<Producto> resultado = productoController.buscarPorPrecio(precioBuscado);

        assertEquals(2, resultado.size());
        assertEquals("Yogurt", resultado.get(0).getNombreProducto());
        assertEquals("Jugo", resultado.get(1).getNombreProducto());
    }
}
