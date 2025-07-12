package com.patomicroservicios.stock_service.controller;

import com.patomicroservicios.stock_service.model.Stock;
import com.patomicroservicios.stock_service.service.IStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stock")
public class StockController {

    @Autowired
    IStockService stockService;

    @PostMapping("/post")
    public ResponseEntity<String> addStock(@RequestBody Stock stock) {
        try {
            stockService.addStock(stock);
            return ResponseEntity.status(201).body("Stock añadido correctamente!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Datos inválidos: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error interno al añadir el stock.");
        }
    }


    @GetMapping("/get/all")
    public ResponseEntity<List<Stock>> getStockList() {
        List<Stock> stocks = stockService.getStockAllProducts();
        if (stocks.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(stocks);
        }
    }


    @GetMapping("/get/{productId}")
    public ResponseEntity<Stock> getStock(@PathVariable Long productId) {
        try {
            Stock stock = stockService.getProductStock(productId);
            return ResponseEntity.ok(stock);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("/put/{productId}/{nuevaCantidad}")
    public ResponseEntity<String> editStock(@PathVariable Long productId, @PathVariable int nuevaCantidad) {
        try {
            stockService.editStock(productId, nuevaCantidad);
            return ResponseEntity.ok("¡El stock se editó correctamente!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body("Producto o stock no encontrado.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error interno al actualizar el stock.");
        }
    }


}
