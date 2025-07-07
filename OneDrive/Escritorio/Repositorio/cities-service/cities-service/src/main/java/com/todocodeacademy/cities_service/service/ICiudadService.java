package com.todocodeacademy.cities_service.service;

import com.todocodeacademy.cities_service.dto.CiudadDTO;

public interface ICiudadService {
    CiudadDTO getCityAndHotelsByIdCity(String nombreCiudad);
}
