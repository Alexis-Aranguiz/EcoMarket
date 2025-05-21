package com.example.EcoMarket;




import com.example.EcoMarket.model.Producto;
import com.example.EcoMarket.repository.ProductoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductoRepositoryTest {

    @Mock
    private ProductoRepository productoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindByNombre_producto() {
        List<Producto> productosMock = List.of(
                new Producto(1, "Aceite de Coco Orgánico", "Aceite de coco prensado en frío, 100% natural", "Aceites Naturales", 150, 8000.0, 200),
                new Producto(2, "Jabón Artesanal de Lavanda", "Jabón biodegradable hecho a mano con esencia natural de lavanda", "Cuidado Personal", 80, 4500.0, 100)
        );

        when(productoRepository.findByNombre_producto("Aceite de Coco Orgánico")).thenReturn(productosMock);

        List<Producto> resultado = productoRepository.findByNombre_producto("Aceite de Coco Orgánico");

        assertNotNull(resultado);
        assertFalse(resultado.isEmpty());
        assertEquals("Aceite de Coco Orgánico", resultado.get(0).getNombre_producto());

        verify(productoRepository, times(1)).findByNombre_producto("Aceite de Coco Orgánico");
    }

    @Test
    void testFindByCategoria() {
        List<Producto> productosMock = List.of(
                new Producto(3, "Té Verde Orgánico", "Té verde cultivado sin pesticidas ni fertilizantes químicos", "Bebidas Naturales", 200, 3500.0, 150),
                new Producto(4, "Miel Orgánica de Ulmo", "Miel 100% natural proveniente del bosque nativo chileno", "Bebidas Naturales", 120, 7000.0, 100)
        );

        when(productoRepository.findByCategoria("Bebidas Naturales")).thenReturn(productosMock);

        List<Producto> resultado = productoRepository.findByCategoria("Bebidas Naturales");

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Bebidas Naturales", resultado.get(0).getCategoria());

        verify(productoRepository, times(1)).findByCategoria("Bebidas Naturales");
    }
}

