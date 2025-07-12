package com.patomicroservicios.stock_service.service;

import com.patomicroservicios.stock_service.dto.ProductoDTO;
import com.patomicroservicios.stock_service.model.Stock;
import com.patomicroservicios.stock_service.repository.IStockRepository;
import com.patomicroservicios.stock_service.repository.productoAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService implements IStockService{

    @Autowired
    IStockRepository stockRepository;

    @Autowired
    productoAPI productoAPI;

    @Override
    public void addStock(Stock stock) {
        stockRepository.save(stock);
    }

    @Override
    public void editStock(Long idProducto, int nuevaCantidad) {
        Stock stock=getProductStock(idProducto);
        stock.setCantidad(nuevaCantidad);
        stockRepository.save(stock);
    }

    @Override
    public Stock getProductStock(Long idProducto) {
        ProductoDTO productoDTO = productoAPI.getProducto(idProducto);
        if(productoDTO==null) throw new IllegalArgumentException("No existe producto con ese id");
        Stock stock=stockRepository.getProductStock(idProducto);
        if(stock==null) throw new IllegalArgumentException("No existe stock registrado con ese id de producto");
        return stock;
    }

    @Override
    public List<Stock> getStockAllProducts() {
        return stockRepository.findAll();
    }
}
