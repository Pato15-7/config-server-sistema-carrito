package com.todocodeacademy.cities_service.repository;

import com.todocodeacademy.cities_service.model.Ciudad;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class CiudadRepository {
    List<Ciudad> listaCiudades = Arrays.asList(
            new Ciudad(1L, "Buenos Aires", "América", "Argentina", "Buenos Aires"),
            new Ciudad(2L, "Barcelona", "Europa", "España", "Cataluña"),
            new Ciudad(3L, "Tokyo", "Asia", "Japón", "Tokyo"),
            new Ciudad(4L, "Nueva York", "América", "Estados Unidos", "Nueva York"),
            new Ciudad(5L, "Sídney", "Oceanía", "Australia", "Nueva Gales del Sur")
    );

    public Ciudad getCity(String nombreCiudad){

        for (Ciudad c:listaCiudades){
            if(c.getNombre().equals(nombreCiudad)) return c;
        }
        return null;
    }

}
