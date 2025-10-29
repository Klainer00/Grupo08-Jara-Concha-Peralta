package huerto.carrito.controller;

import huerto.carrito.modelo.ItemCarrito;
import huerto.carrito.service.CarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carrito")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    // Ver el carrito de un usuario
    @GetMapping("/{usuarioId}")
    public List<ItemCarrito> obtenerCarrito(@PathVariable Long usuarioId) {
        return carritoService.verCarrito(usuarioId);
    }

    // Agregar un item al carrito
    @PostMapping
    public ResponseEntity<ItemCarrito> agregarItem(@RequestBody ItemCarrito item) {
        ItemCarrito nuevoItem = carritoService.agregarItem(item);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoItem);
    }

    // Eliminar un item del carrito
    @DeleteMapping("/{itemId}")
    public ResponseEntity<Void> eliminarItem(@PathVariable Long itemId) {
        carritoService.eliminarItem(itemId);
        return ResponseEntity.noContent().build();
    }
}