package com.todocodeacademy.cities_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CiudadDTO {
        private Long id;
        private String nombre;
        private String continente;
        private String pais;
        private String provincia;
        private List<HotelDTO> listaHoteles;
}
