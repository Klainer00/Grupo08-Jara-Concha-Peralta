package huerto.controller;
// import org.springframework.security.access.prepost.PreAuthorize; // Eliminado
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/carrito")
public class CarritoController {

    @GetMapping
    // @PreAuthorize(...) // Eliminado
    public String verCarrito() {
        return "Viendo el carrito (PÚBLICO)";
    }

    @PostMapping("/agregar")
    // @PreAuthorize(...) // Eliminado
    public String agregarAlCarrito(@RequestBody Map<String, Object> payload) {
        // Acepta el JSON: {"Idusuario": 1, "Idproducto": 2, "cantidad": 3}
        return "Producto agregado (PÚBLICO)";
    }
}