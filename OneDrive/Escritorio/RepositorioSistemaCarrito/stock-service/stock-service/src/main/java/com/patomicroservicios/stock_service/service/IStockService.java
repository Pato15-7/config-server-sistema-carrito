package com.patomicroservicios.stock_service.service;

import com.patomicroservicios.stock_service.model.Stock;

import java.util.List;

public interface IStockService {
    void addStock(Stock stock);
    void editStock(Long idProducto, int nuevaCantidad);
    Stock getProductStock(Long idProducto);
    List<Stock> getStockAllProducts();
}
