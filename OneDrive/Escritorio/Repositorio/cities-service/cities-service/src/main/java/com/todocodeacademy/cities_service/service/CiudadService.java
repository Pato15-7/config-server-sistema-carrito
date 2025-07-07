package com.todocodeacademy.cities_service.service;

import com.netflix.discovery.converters.Auto;
import com.todocodeacademy.cities_service.dto.CiudadDTO;
import com.todocodeacademy.cities_service.model.Ciudad;
import com.todocodeacademy.cities_service.repository.CiudadRepository;
import com.todocodeacademy.cities_service.repository.hotelAPI;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CiudadService implements ICiudadService{

    @Autowired
    CiudadRepository ciudadRepository;

    @Autowired
    hotelAPI hotelAPI;

    @Override
    @CircuitBreaker(name="hotels-service",fallbackMethod = "fallbackgetCityAndHotelsByIdCity")
    public CiudadDTO getCityAndHotelsByIdCity(String nombreCiudad) {
        Ciudad city=ciudadRepository.getCity(nombreCiudad);
        CiudadDTO ciudadDTO= new CiudadDTO(city.getId(),city.getNombre(),city.getContinente(),city.getPais(),city.getProvincia(),null);

        ciudadDTO.setListaHoteles(hotelAPI.getHotelesByIdCiudad(city.getId()));
        return ciudadDTO;
    }

    public CiudadDTO fallbackgetCityAndHotelsByIdCity(String nombreCiudad, Throwable throwable){
        System.out.println("⚠️ Fallback activado por excepción: " + throwable.getMessage());
        return new CiudadDTO(888L,"Fallido","Fallido","Fallido","Fallido", null);
    }

    public void createException(){
        throw new IllegalArgumentException("Prueba Resilience y Circuit Breaker");
    }
}
