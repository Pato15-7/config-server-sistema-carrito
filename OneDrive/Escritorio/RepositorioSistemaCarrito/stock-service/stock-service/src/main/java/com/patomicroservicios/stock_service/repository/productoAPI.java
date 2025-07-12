package com.patomicroservicios.stock_service.repository;

import com.patomicroservicios.stock_service.dto.ProductoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "productos-service")
public interface productoAPI {

    @GetMapping("/producto/get/{codigoProducto}")
    public ProductoDTO getProducto(@PathVariable Long codigoProducto);
}
