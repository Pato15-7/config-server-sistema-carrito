package com.patomicroservicios.ventas_service.repository;

import com.patomicroservicios.ventas_service.dto.CarritoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "carrito-service")
public interface carritoAPI {

    @GetMapping("carrito/get/{idCarrito}")
    public CarritoDTO getCarrito(@PathVariable Long idCarrito);
}
