package com.patomicroservicios.carrito_service.repository;

import com.patomicroservicios.carrito_service.dto.StockDTO;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "stock-service")
public interface stockAPI {

    @GetMapping("/stock/get/{codigoProducto}")
    StockDTO getCantidadStockPoducto(@PathVariable Long codigoProducto);

    @PutMapping("/stock/put/{productId}/{nuevaCantidad}")
    void editStock(@PathVariable Long productId, @PathVariable int nuevaCantidad);

}
