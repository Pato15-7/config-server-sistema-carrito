package com.patomicroservicios.productos_service.controller;

import com.patomicroservicios.productos_service.dto.ProductoDTO;
import com.patomicroservicios.productos_service.model.Producto;
import com.patomicroservicios.productos_service.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    IProductoService productoService;

    @Value("${server.port}")
    private int serverPort;

    @GetMapping("/get/{codigoProducto}")
    public ResponseEntity<?> getProducto(@PathVariable Long codigoProducto) {
        try {
            Producto producto = productoService.getProducto(codigoProducto);
            if (producto == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado");
            }
            return ResponseEntity.ok(new ProductoDTO(producto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener producto: " + e.getMessage());
        }
    }

    @PostMapping("/get/by-ids")
    public ResponseEntity<?> getProductosByIds(@RequestBody List<Long> ids) {
        try {
            List<ProductoDTO> productos = productoService.getProductsById(ids);
            return ResponseEntity.ok(productos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener productos por IDs: " + e.getMessage());
        }
    }


    @GetMapping("/get/all")
    public ResponseEntity<?> getAllProductos() {
        try {
            System.out.println("server: " + serverPort);
            List<Producto> productos = productoService.getAllProductos();
            return ResponseEntity.ok(productos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener todos los productos: " + e.getMessage());
        }
    }

    @PostMapping("/post")
    public ResponseEntity<?> addProduct(@RequestBody Producto producto) {
        try {
            productoService.altaProducto(producto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Producto creado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear producto: " + e.getMessage());
        }
    }

    @PutMapping("/put/{codigoProducto}")
    public ResponseEntity<?> putProduct(@PathVariable Long codigoProducto, @RequestBody Producto producto) {
        try {
            productoService.editarProducto(producto, codigoProducto);
            return ResponseEntity.ok("Producto editado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al editar producto: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{codigoProducto}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long codigoProducto) {
        try {
            productoService.eliminarProducto(codigoProducto);
            return ResponseEntity.ok("Producto eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al eliminar producto: " + e.getMessage());
        }
    }
}
