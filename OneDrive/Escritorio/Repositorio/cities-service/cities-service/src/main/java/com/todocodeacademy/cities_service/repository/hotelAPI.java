package com.todocodeacademy.cities_service.repository;

import com.todocodeacademy.cities_service.dto.HotelDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "hotels-service")
public interface hotelAPI {

    @GetMapping("/hotel/get/{idCiudad}")
    public List<HotelDTO> getHotelesByIdCiudad(@PathVariable Long idCiudad);
}
