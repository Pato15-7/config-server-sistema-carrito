package com.todocodeacademy.cities_service.controller;

import com.todocodeacademy.cities_service.dto.CiudadDTO;
import com.todocodeacademy.cities_service.service.ICiudadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ciudad")
public class CiudadController {

    @Autowired
    ICiudadService ciudadService;

    @GetMapping("/get/{nombreCiudad}")
    public CiudadDTO getCityAndHotelsByIdCity(@PathVariable String nombreCiudad){
        return ciudadService.getCityAndHotelsByIdCity(nombreCiudad);
    }
}
