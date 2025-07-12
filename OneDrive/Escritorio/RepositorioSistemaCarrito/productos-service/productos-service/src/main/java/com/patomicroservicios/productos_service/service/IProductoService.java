package com.patomicroservicios.productos_service.service;

import com.patomicroservicios.productos_service.dto.ProductoDTO;
import com.patomicroservicios.productos_service.model.Producto;

import java.util.List;

public interface IProductoService {
    void altaProducto(Producto producto);
    void eliminarProducto(Long codigoProducto);
    void editarProducto(Producto producto, Long codigoProducto);
    List<Producto> getAllProductos();
    Producto getProducto(Long codigoProducto);
    List<ProductoDTO> getProductsById(List<Long> ids);
}
