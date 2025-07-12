package com.patomicroservicios.carrito_service.controller;

import com.patomicroservicios.carrito_service.dto.CarritoDTO;
import com.patomicroservicios.carrito_service.dto.ProductoDTO;
import com.patomicroservicios.carrito_service.model.Carrito;
import com.patomicroservicios.carrito_service.service.ICarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carrito")
public class CarritoContoller {

    @Autowired
    ICarritoService carritoService;

    @GetMapping("/get/{idCarrito}")
    public ResponseEntity<?> getCarrito(@PathVariable Long idCarrito){
        try {
            CarritoDTO carritoDTO= carritoService.getCarritoandProductos(idCarrito);
            return ResponseEntity.ok(carritoDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/get/all")
    public List<CarritoDTO> getAllCarritos(){
        return carritoService.getAllCarritos();
    }

    @GetMapping("/get/productos/{idCarrito}")
    public ResponseEntity<?> getProductosCarrito(@PathVariable Long idCarrito){
        try {
            List<ProductoDTO> listaProductos= carritoService.getProductosCarrito(idCarrito);
            return ResponseEntity.ok(listaProductos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/post")
    public String altaCarrito(){
        carritoService.altaCarrito();
        return "El carrito se agrego correctamente!";
    }

    @DeleteMapping("/delete/{idCarrito}")
    public ResponseEntity<?> eliminarCarrito(@PathVariable Long idCarrito){
        try {
            carritoService.eliminarCarrito(idCarrito);
            return ResponseEntity.ok("El carrito se elimino correctamente!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/post/productos/{idCarrito}/{idProducto}/{cantidad}")
    public ResponseEntity<?> addProductosCarrito(@PathVariable Long idCarrito, @PathVariable Long idProducto, @PathVariable int cantidad){
        try {
            carritoService.agregarProducto(idCarrito,idProducto, cantidad);
            return ResponseEntity.ok("Se agregaron productos al carrito!!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/put/productos/{idCarrito}/{idProducto}/{cantidad}")
    public ResponseEntity<?> editarCantidadProductoCarrito(@PathVariable Long idCarrito, @PathVariable Long idProducto, @PathVariable int cantidad){
        try {
            carritoService.editarCantidadProducto(idCarrito,idProducto, cantidad);
            return ResponseEntity.ok("Se modifico la cantidad del producto en el carrito!!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/productos/{idCarrito}/{idProducto}")
    public ResponseEntity<?> deleteProductosCarritos(@PathVariable Long idCarrito, @PathVariable Long idProducto){
        try {
            carritoService.eliminarProducto(idCarrito, idProducto);
            return ResponseEntity.ok("Se elimino el producto del carrito!!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
