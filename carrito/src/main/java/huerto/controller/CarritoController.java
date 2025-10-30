package huerto.controller;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carrito")
public class CarritoController {

    @GetMapping
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public String verCarrito() {
        return "Viendo el carrito";
    }

    @PostMapping("/agregar")
    @PreAuthorize("hasAuthority('USER')")
    public String agregarAlCarrito() {
        return "Producto agregado";
    }
}