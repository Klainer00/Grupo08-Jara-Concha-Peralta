package huerto.service;

import huerto.dto.ProductoRequestDto;
import huerto.dto.ProductoResponseDto;
import huerto.model.CategoriaEntity;
import huerto.model.Producto;
import huerto.repository.CategoriaRepository;
import huerto.repository.ProductoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    // --- Método Helper para convertir Entidad a DTO ---
    private ProductoResponseDto convertirADto(Producto producto) {
        return ProductoResponseDto.builder()
                .id(producto.getId())
                .nombre(producto.getNombre())
                .descripcion(producto.getDescripcion())
                .precio(producto.getPrecio())
                .stock(producto.getStock())
                .categoriaNombre(producto.getCategoria().getNombre()) // Obtenemos el nombre
                .build();
    }

    public ProductoResponseDto crearProducto(ProductoRequestDto dto) {
        // 1. Buscar la entidad Categoria
        CategoriaEntity categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new EntityNotFoundException("Categoría no encontrada con id: " + dto.getCategoriaId()));
        
        Producto producto = new Producto();
        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setPrecio(dto.getPrecio());
        producto.setStock(dto.getStock());
        producto.setCategoria(categoria);
        
        Producto productoGuardado = productoRepository.save(producto);
        return convertirADto(productoGuardado);
    }

    public List<ProductoResponseDto> obtenerTodosLosProductos() {
        return productoRepository.findAll()
                .stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }

    public ProductoResponseDto obtenerProductoPorId(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado con id: " + id));
        return convertirADto(producto);
    }

    public ProductoResponseDto actualizarProducto(Long id, ProductoRequestDto dto) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado con id: " + id));

        CategoriaEntity categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new EntityNotFoundException("Categoría no encontrada con id: " + dto.getCategoriaId()));

        // 3. Actualizar los campos
        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setPrecio(dto.getPrecio());
        producto.setStock(dto.getStock());
        producto.setCategoria(categoria);
        
        // 4. Guardar y convertir
        Producto productoActualizado = productoRepository.save(producto);
        return convertirADto(productoActualizado);
    }

    // --- DELETE ---
    public void borrarProducto(Long id) {
        if (!productoRepository.existsById(id)) {
            throw new EntityNotFoundException("Producto no encontrado con id: " + id);
        }
        productoRepository.deleteById(id);
    }
}