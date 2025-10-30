package main.java.huerto.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @GetMapping
    public String verTodosLosProductos() {
        return "Lista de productos";
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')") 
    public String crearProducto() {
        return "Producto creado (solo ADMIN)";
    }
}