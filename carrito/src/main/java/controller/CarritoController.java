package huerto.carrito.controller;

import huerto.carrito.dto.ItemCarritoDTO;
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


    // Ej: @PreAuthorize("#usuarioId == authentication.principal.id or hasRole('ADMIN')")
    @GetMapping("/{usuarioId}")
    public List<ItemCarritoDTO> obtenerCarrito(@PathVariable Long usuarioId) {
        return carritoService.verCarrito(usuarioId);
    }

    // Ej: @PreAuthorize("#item.usuarioId == authentication.principal.id")
    @PostMapping
    public ResponseEntity<ItemCarritoDTO> agregarItem(@RequestBody ItemCarritoDTO item) {
        ItemCarritoDTO nuevoItem = carritoService.agregarItem(item);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoItem);
    }
    
    // Ej: @PreAuthorize("...lógica para verificar que el itemId pertenece al usuario autenticado...")
    @DeleteMapping("/{itemId}")
    public ResponseEntity<Void> eliminarItem(@PathVariable Long itemId) {
        if (carritoService.eliminarItem(itemId)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}