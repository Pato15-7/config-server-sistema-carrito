package com.patomicroservicios.ventas_service.service;

import com.patomicroservicios.ventas_service.dto.CarritoDTO;
import com.patomicroservicios.ventas_service.dto.ProductoDTO;
import com.patomicroservicios.ventas_service.dto.VentaDTO;
import com.patomicroservicios.ventas_service.model.Venta;
import com.patomicroservicios.ventas_service.repository.IVentaRepository;
import com.patomicroservicios.ventas_service.repository.carritoAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VentaService implements IVentaService{

    @Autowired
    IVentaRepository ventaRepository;

    @Autowired
    carritoAPI carritoAPI;

    @Override
    public void altaVenta(Venta venta) {
        CarritoDTO carrito = carritoAPI.getCarrito(venta.getIdCarrito());
        if(carrito==null) throw new IllegalArgumentException("No existe carrito con ese id");
        ventaRepository.save(venta);
    }

    @Override
    public void eliminarVenta(Long idVenta) {
        Venta ven= ventaRepository.findById(idVenta).orElseThrow(() -> new IllegalArgumentException("Venta no encontrada"));
        ventaRepository.deleteById(ven.getId());
    }

    @Override
    public List<VentaDTO> getAll() {
        List<Venta> listaVentas=ventaRepository.findAll();
        CarritoDTO carrito = new CarritoDTO();
        List<VentaDTO> listaVentaDTO=new ArrayList<>();
        for(Venta ven:listaVentas){
                carrito = carritoAPI.getCarrito(ven.getIdCarrito());
                VentaDTO ventaDTO = new VentaDTO(ven.getId(), ven.getFecha(), carrito);
                listaVentaDTO.add(ventaDTO);
        }
        return listaVentaDTO;
    }

    @Override
    public VentaDTO getVenta(Long idVenta) {
        Venta ven= ventaRepository.findById(idVenta).orElseThrow(() -> new IllegalArgumentException("Venta no encontrada"));
        CarritoDTO carritoDTO= carritoAPI.getCarrito(ven.getIdCarrito());
        VentaDTO ventaDTO= new VentaDTO(ven.getId(),ven.getFecha(),carritoDTO);
        return ventaDTO;
    }
}
