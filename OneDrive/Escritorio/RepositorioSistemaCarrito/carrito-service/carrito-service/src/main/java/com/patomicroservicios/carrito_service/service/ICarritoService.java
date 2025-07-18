package com.patomicroservicios.carrito_service.service;

import com.patomicroservicios.carrito_service.dto.CarritoDTO;
import com.patomicroservicios.carrito_service.dto.ProductoDTO;
import com.patomicroservicios.carrito_service.model.Carrito;

import java.util.List;

public interface ICarritoService {
    void agregarProducto(Long idCarrito, Long idProducto, int cantidad);
    void eliminarProducto(Long idCarrito, Long idProducto);
    CarritoDTO getCarritoandProductos(Long idCarrito);
    List<ProductoDTO> getProductosCarrito(Long idCarrito);
    List<CarritoDTO> getAllCarritos();
    void altaCarrito();
    void eliminarCarrito(Long idCarrito);
    void editarCantidadProducto(Long idCarrito, Long idProducto, int cantidad);
}
