package com.patomicroservicios.carrito_service.service;

import com.patomicroservicios.carrito_service.dto.CarritoDTO;
import com.patomicroservicios.carrito_service.dto.ProductoDTO;
import com.patomicroservicios.carrito_service.dto.StockDTO;
import com.patomicroservicios.carrito_service.model.Carrito;
import com.patomicroservicios.carrito_service.model.ProductoCantidad;
import com.patomicroservicios.carrito_service.repository.ICarritoRepository;
import com.patomicroservicios.carrito_service.repository.ProductoAPI;
import com.patomicroservicios.carrito_service.repository.stockAPI;
import feign.FeignException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CarritoService implements ICarritoService{

    @Autowired
    ICarritoRepository carritoRepository;

    @Autowired
    ProductoAPI productoAPI;

    @Autowired
    stockAPI stockAPI;

    @Override
    public void agregarProducto(Long idCarrito, Long idProducto, int cantidad) {
        Carrito carrito = carritoRepository.findById(idCarrito).orElseThrow(() -> new IllegalArgumentException("Carrito no encontrado"));
            ProductoDTO productoDTO = productoAPI.getProducto(idProducto);
            if (productoDTO == null) throw new IllegalArgumentException("No existe producto con ese id");
        StockDTO stockDTO = validarStock(idProducto, cantidad);
        ProductoCantidad productoCantidad=new ProductoCantidad(idProducto, cantidad);
            stockAPI.editStock(idProducto,stockDTO.getCantidad()-cantidad);
            carrito.getListaProductos().add(productoCantidad);
            carrito=actualizarSumaTotal(carrito);
            carritoRepository.save(carrito);
    }

    private StockDTO validarStock(Long idProducto, int cantidad) {
        StockDTO stockDTO=stockAPI.getCantidadStockPoducto(idProducto);
        if(cantidad >stockDTO.getCantidad()) throw new IllegalArgumentException("La cantidad ingresada es mayor que el stock disponible");
        return stockDTO;
    }

    private Carrito actualizarSumaTotal(Carrito carrito) {
        double suma = 0;
        for(ProductoCantidad pc : carrito.getListaProductos()){
            ProductoDTO productoDTO=productoAPI.getProducto(pc.getIdProducto());
            suma+=productoDTO.getPrecioIndividual()*pc.getCantidad();
        }
        carrito.setPrecioTotal(suma);
        return carrito;
    }

    @Override
    @Transactional
    public void eliminarProducto(Long idCarrito, Long idProducto) {
        Carrito carrito = carritoRepository.findById(idCarrito)
                .orElseThrow(() -> new IllegalArgumentException("Carrito no encontrado"));

// 🔁 1. Buscar la cantidad antes de eliminar
        int cantidad = carrito.getListaProductos().stream()
                .filter(p -> p.getIdProducto().equals(idProducto))
                .mapToInt(ProductoCantidad::getCantidad)
                .findFirst()
                .orElse(0);

// 🔁 2. Eliminar el producto
        boolean eliminado = carrito.getListaProductos()
                .removeIf(p -> p.getIdProducto().equals(idProducto));

        if (eliminado) {
            StockDTO stockDTO = stockAPI.getCantidadStockPoducto(idProducto);

            // 🔁 3. Actualizar stock correctamente
            stockAPI.editStock(idProducto, cantidad + stockDTO.getCantidad());

            carrito = actualizarSumaTotal(carrito);
            carritoRepository.save(carrito);
        } else {
            throw new IllegalArgumentException("El producto no estaba en el carrito");
        }

    }

    @Override
    public CarritoDTO getCarritoandProductos(Long idCarrito) {
        Carrito carrito=carritoRepository.findById(idCarrito).orElseThrow(() -> new IllegalArgumentException("Carrito no encontrado"));
        List<ProductoDTO> listaProductos= new ArrayList<>();
        for(ProductoCantidad pc : carrito.getListaProductos()){
            listaProductos.add(productoAPI.getProducto(pc.getIdProducto()));
        }
        CarritoDTO carritoDTO= new CarritoDTO(carrito.getId(),carrito.getPrecioTotal(),listaProductos);
        return carritoDTO;
    }

    @Override
    public List<ProductoDTO> getProductosCarrito(Long idCarrito) {
        Carrito carrito=carritoRepository.findById(idCarrito).orElseThrow(() -> new IllegalArgumentException("Carrito no encontrado"));
        List<ProductoDTO> listaProductos= new ArrayList<>();
        for(ProductoCantidad pc : carrito.getListaProductos()){
            listaProductos.add(productoAPI.getProducto(pc.getIdProducto()));
        }
        return listaProductos;
    }

    @Override
    public List<CarritoDTO> getAllCarritos() {
        List<Carrito> lista= carritoRepository.findAll();
        List<CarritoDTO> listaCarritoDTO = new ArrayList<>();
        for(Carrito car : lista){
            CarritoDTO carritoDTO=getCarritoandProductos(car.getId());
            listaCarritoDTO.add(carritoDTO);
        }
        return listaCarritoDTO;
    }

    @Override
    public void altaCarrito() {
        Carrito carrito= new Carrito();
        carritoRepository.save(carrito);
    }

    @Override
    public void eliminarCarrito(Long idCarrito) {
        Carrito carrito=carritoRepository.findById(idCarrito).orElseThrow(() -> new IllegalArgumentException("Carrito no encontrado"));
        carritoRepository.deleteById(idCarrito);
    }

    @Override
    public void editarCantidadProducto(Long idCarrito, Long idProducto, int cantidad) {
        Carrito carrito=carritoRepository.findById(idCarrito).orElseThrow(() -> new IllegalArgumentException("Carrito no encontrado"));
        List<ProductoCantidad> productoCantidadList= new ArrayList<>();
        for(ProductoCantidad pc : carrito.getListaProductos()){
            if(pc.getIdProducto().equals(idProducto)){
                int cantidadAnterior=pc.getCantidad();
                pc.setCantidad(cantidad);
                int diferencia = cantidad - cantidadAnterior;
                StockDTO stockDTO=stockAPI.getCantidadStockPoducto(idProducto);
                if (diferencia > 0) {
                    if (stockDTO.getCantidad() < diferencia) {
                        throw new IllegalArgumentException("Stock insuficiente para actualizar el producto");
                    }
                }
                stockAPI.editStock(idProducto,stockDTO.getCantidad()+cantidadAnterior-cantidad);
            }
            productoCantidadList.add(pc);
        }
        carrito.setListaProductos(productoCantidadList);
        actualizarSumaTotal(carrito);
        carritoRepository.save(carrito);
    }
}