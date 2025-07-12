package com.patomicroservicios.productos_service.dto;

import com.patomicroservicios.productos_service.model.Producto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductoDTO {
    private Long codigo;
    private String nombre;
    private String marca;
    private double precioIndividual;

    public ProductoDTO(Producto producto) {
        this.codigo = producto.getCodigo();
        this.nombre = producto.getNombre();
        this.marca=producto.getMarca();
        this.precioIndividual = producto.getPrecioIndividual();
    }
}