package com.patomicroservicios.ventas_service.service;

import com.patomicroservicios.ventas_service.dto.VentaDTO;
import com.patomicroservicios.ventas_service.model.Venta;

import java.util.List;

public interface IVentaService {
    void altaVenta(Venta venta);
    void eliminarVenta(Long idVenta);
    List<VentaDTO> getAll();
    VentaDTO getVenta(Long idVenta);
}
