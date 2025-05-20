package com.example.EcoMarket.repository;
import com.example.EcoMarket.model.Producto;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
@Repository

public interface ProductoRepository {
    //Arreglo que guarda los productos
    private List<Producto> listaProducto = new ArrayList<>();


    //Retorna lista de productos
    public List<Producto> obtenerProductos(){
        return listaProducto;
    }

    //Buscar producto por id
    public Producto buscarID(int id_producto){
        for(Producto producto:listaProducto){
            if(producto.getId_producto()==id_producto){
                return producto;
            }
        }
        return null;
    }



    //Bucar por nombre de producto
    public Producto buscarNombre(String nombre_producto){
        for(Producto producto:listaProducto){
            if(producto.getNombre_producto().equals(nombre_producto)){
                return producto;
            }
        }
        return null;
    }

    //Metodo guardar
    public Producto guardarProducto(Producto producto){
        listaProducto.add(producto);
        return producto;
    }

    //MetodoActualizar
    public Producto actualizarProducto(Producto producto){
        int id_producto = 0;
        int idPosicion = 0;
        for(int i=0;i<listaProducto.size();i++){
            if(listaProducto.get(i).getId_producto()==producto.getId_producto()){
                idPosicion = i;
            }
        }

    }
}
