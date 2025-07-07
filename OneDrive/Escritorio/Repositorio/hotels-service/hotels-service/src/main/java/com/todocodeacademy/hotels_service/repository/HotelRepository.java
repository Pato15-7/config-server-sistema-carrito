package com.todocodeacademy.hotels_service.repository;

import com.todocodeacademy.hotels_service.model.Hotel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class HotelRepository {

    List<Hotel> listaHoteles = Arrays.asList(
            // Buenos Aires (idCiudad = 1)
            new Hotel(1L, "Gran Hotel Buenos Aires", 5, 1L),
            new Hotel(2L, "Hotel Palermo", 4, 1L),
            new Hotel(3L, "Hostel Recoleta", 3, 1L),

            // Barcelona (idCiudad = 2)
            new Hotel(4L, "Hotel Sagrada Familia", 5, 2L),
            new Hotel(5L, "Hotel Gótico", 4, 2L),
            new Hotel(6L, "Raval Inn", 3, 2L),

            // Tokyo (idCiudad = 3)
            new Hotel(7L, "Tokyo Tower Hotel", 5, 3L),
            new Hotel(8L, "Shibuya Inn", 4, 3L),
            new Hotel(9L, "Capsule Stay", 2, 3L),

            // Nueva York (idCiudad = 4)
            new Hotel(10L, "Manhattan Grand", 5, 4L),
            new Hotel(11L, "Brooklyn Stay", 4, 4L),
            new Hotel(12L, "Bronx Motel", 2, 4L),

            // Sídney (idCiudad = 5)
            new Hotel(13L, "Harbour View Hotel", 5, 5L),
            new Hotel(14L, "Bondi Beach Inn", 4, 5L),
            new Hotel(15L, "Sydney Lodge", 3, 5L)
    );


    public List<Hotel> getHotelsByIdCiudad(Long idCiudad){
        List<Hotel> nuevaListaHoteles= new ArrayList<>();
        for (Hotel h : listaHoteles){
            if(h.getIdCiudad().equals(idCiudad)) nuevaListaHoteles.add(h);
        }
        return nuevaListaHoteles;
    }
}
