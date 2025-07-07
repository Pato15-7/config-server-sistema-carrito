package com.todocodeacademy.hotels_service.service;

import com.todocodeacademy.hotels_service.model.Hotel;

import java.util.List;

public interface IHotelService {
    public List<Hotel> getHotelsByIdCiudad(Long idCiudad);
}
