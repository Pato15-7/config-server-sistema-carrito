package com.patomicroservicios.ventas_service.controller;

import com.patomicroservicios.ventas_service.dto.VentaDTO;
import com.patomicroservicios.ventas_service.model.Venta;
import com.patomicroservicios.ventas_service.service.IVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ventas")
public class VentaController {

    @Autowired
    IVentaService ventaService;

    @GetMapping("/get/all")
    public List<VentaDTO> getAll(){
        return ventaService.getAll();
    }

    @GetMapping("/get/{idVenta}")
    public ResponseEntity<?> getVenta(@PathVariable Long idVenta){
        try{
            VentaDTO venta=ventaService.getVenta(idVenta);
            return ResponseEntity.ok(venta);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{idVenta}")
    public ResponseEntity<?> deleteVenta(@PathVariable Long idVenta){
        try{
            ventaService.eliminarVenta(idVenta);
            return ResponseEntity.ok("Se elimino la venta!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/post")
    public String addVenta(@RequestBody Venta venta){
        ventaService.altaVenta(venta);
        return "Se registro la venta!";
    }

}
