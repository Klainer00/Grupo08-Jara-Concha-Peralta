package huerto.core.service;

import huerto.core.dto.ProductoDTO;
import huerto.core.entity.CategoriaEntity;
import huerto.core.entity.ProductoEntity;
import huerto.core.repository.CategoriaRepository;
import huerto.core.repository.ProductoRepository;
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

    public ProductoDTO crearProducto(ProductoDTO productoDTO) {
        ProductoEntity entity = toEntity(productoDTO);
        entity = productoRepository.save(entity);
        return toDTO(entity);
    }

    public List<ProductoDTO> obtenerProductosPorCategoria(Long categoriaId) {
        return productoRepository.findByCategoriaId(categoriaId).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
    
    // --- Conversiones ---

    private ProductoDTO toDTO(ProductoEntity entity) {
        ProductoDTO dto = new ProductoDTO();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setDescripcion(entity.getDescripcion());
        dto.setPrecio(entity.getPrecio());
        if (entity.getCategoria() != null) {
            dto.setCategoriaId(entity.getCategoria().getId());
            dto.setNombreCategoria(entity.getCategoria().getNombre());
        }
        return dto;
    }

    private ProductoEntity toEntity(ProductoDTO dto) {
        ProductoEntity entity = new ProductoEntity();
        // 'id' se ignora para creación
        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());
        entity.setPrecio(dto.getPrecio());
        
        // Buscamos la entidad Categoria
        CategoriaEntity categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
        entity.setCategoria(categoria);
        return entity;
    }
}