package huerto.controller;

import huerto.dto.ProductoRequestDto;
import huerto.dto.ProductoResponseDto;
import huerto.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    // CREATE (Solo ADMIN)
    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")  // <-- CORREGIDO
    public ResponseEntity<ProductoResponseDto> crearProducto(@Valid @RequestBody ProductoRequestDto dto) {
        ProductoResponseDto productoCreado = productoService.crearProducto(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productoCreado);
    }

    // READ (All) - (Público)
    @GetMapping
    public ResponseEntity<List<ProductoResponseDto>> verTodosLosProductos() {
        List<ProductoResponseDto> productos = productoService.obtenerTodosLosProductos();
        return ResponseEntity.ok(productos);
    }

    // READ (By ID) - (Público)
    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponseDto> obtenerProductoPorId(@PathVariable Long id) {
        ProductoResponseDto producto = productoService.obtenerProductoPorId(id);
        return ResponseEntity.ok(producto);
    }

    // UPDATE (Solo ADMIN)
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')") // <-- CORREGIDO
    public ResponseEntity<ProductoResponseDto> actualizarProducto(@PathVariable Long id, @Valid @RequestBody ProductoRequestDto dto) {
        ProductoResponseDto productoActualizado = productoService.actualizarProducto(id, dto);
        return ResponseEntity.ok(productoActualizado);
    }

    // DELETE (Solo ADMIN)
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')") // <-- CORREGIDO
    public ResponseEntity<Void> borrarProducto(@PathVariable Long id) {
        productoService.borrarProducto(id);
        return ResponseEntity.noContent().build(); // Retorna 204 No Content
    }
}