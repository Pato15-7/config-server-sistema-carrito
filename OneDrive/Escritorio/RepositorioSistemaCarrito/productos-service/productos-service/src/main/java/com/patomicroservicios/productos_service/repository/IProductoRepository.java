package com.patomicroservicios.productos_service.repository;

import com.patomicroservicios.productos_service.dto.ProductoDTO;
import com.patomicroservicios.productos_service.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductoRepository extends JpaRepository<Producto,Long> {

    @Query("SELECT p FROM Producto p Where p.id IN :ids")
    List<ProductoDTO> getProductsById(List<Long> ids);

}
