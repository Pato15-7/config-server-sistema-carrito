package com.todocodeacademy.hotels_service.service;

import com.netflix.discovery.converters.Auto;
import com.todocodeacademy.hotels_service.model.Hotel;
import com.todocodeacademy.hotels_service.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService implements IHotelService{

    @Autowired
    HotelRepository hotelRepository;

    @Override
    public List<Hotel> getHotelsByIdCiudad(Long idCiudad) {
        return hotelRepository.getHotelsByIdCiudad(idCiudad);
    }
}
