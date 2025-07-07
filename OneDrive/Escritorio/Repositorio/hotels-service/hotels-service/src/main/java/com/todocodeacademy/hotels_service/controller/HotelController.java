package com.todocodeacademy.hotels_service.controller;

import com.todocodeacademy.hotels_service.model.Hotel;
import com.todocodeacademy.hotels_service.service.IHotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    @Autowired
    IHotelService hotelService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/get/{idCiudad}")
    public List<Hotel> getHotelsByIdCiudad(@PathVariable Long idCiudad){
        System.out.println("puerto: "+serverPort);
        return hotelService.getHotelsByIdCiudad(idCiudad);
    }
}
