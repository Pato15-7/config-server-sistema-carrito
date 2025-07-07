package com.todocodeacademy.hotels_service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {
    private Long id;
    private String nombre;
    private int cantEstrellas;
    private Long idCiudad;
}
