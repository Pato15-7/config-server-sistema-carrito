package com.patomicroservicios.productos_service.service;

import com.patomicroservicios.productos_service.dto.ProductoDTO;
import com.patomicroservicios.productos_service.model.Producto;
import com.patomicroservicios.productos_service.repository.IProductoRepository;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;



import java.util.List;

@Service
public class ProductoService implements IProductoService{

    @Autowired
    IProductoRepository productoRepository;

    @Override
    public void altaProducto(Producto producto) {
        productoRepository.save(producto);
    }

    @Override
    public void eliminarProducto(Long codigoProducto) {
        Producto producto=getProducto(codigoProducto);
        productoRepository.deleteById(codigoProducto);
    }

    @Override
    public void editarProducto(Producto producto, Long codigoProducto) {
        Producto product=getProducto(codigoProducto);
        product.setNombre(producto.getNombre());
        product.setMarca(producto.getMarca());
        product.setPrecioIndividual(producto.getPrecioIndividual());
        productoRepository.save(product);
    }

    @Override
    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    @Override
    @CircuitBreaker(name = "default", fallbackMethod = "fallbackProductoNoEncontrado")
    @Retry(name = "retryGetProducto")
    public Producto getProducto(Long codigoProducto) {
        Producto producto=productoRepository.findById(codigoProducto).orElse(null);
        if(producto!=null) return producto;
        else throw new RuntimeException("Producto no encontrado");
    }

    public List<ProductoDTO> getProductsById(List<Long> ids){
        return productoRepository.getProductsById(ids);
    }

    public Producto fallbackProductoNoEncontrado(Throwable throwable){
        System.out.println("⚠️ Fallback activado por excepción: " + throwable.getMessage());
        return new Producto(888L, "Producto no encontrado", "Producto no encontrado", 999);
    }
}
