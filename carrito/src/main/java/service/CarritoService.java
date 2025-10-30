package huerto.carrito.service;

import huerto.carrito.entity.ItemCarritoEntity; 
import huerto.carrito.dto.ItemCarritoDTO; 
import huerto.carrito.repository.CarritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarritoService {

    @Autowired
    private CarritoRepository carritoRepository;

    public List<ItemCarritoDTO> verCarrito(Long usuarioId) {
        return carritoRepository.findByUsuarioId(usuarioId).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public ItemCarritoDTO agregarItem(ItemCarritoDTO itemDTO) {
        if (itemDTO.getUsuarioId() == null || itemDTO.getProductoId() == null) {
            throw new RuntimeException("ID de usuario y ID de producto no pueden ser nulos");
        }

        Optional<ItemCarritoEntity> itemExistenteOpt = carritoRepository.findByUsuarioIdAndProductoId(
                itemDTO.getUsuarioId(),
                itemDTO.getProductoId()
        );

        if (itemExistenteOpt.isPresent()) {
            ItemCarritoEntity itemExistente = itemExistenteOpt.get();
            itemExistente.setCantidad(itemExistente.getCantidad() + itemDTO.getCantidad());
            ItemCarritoEntity guardado = carritoRepository.save(itemExistente);
            return toDTO(guardado);
        } else {
            if (itemDTO.getCantidad() <= 0) {
                itemDTO.setCantidad(1);
            }
            ItemCarritoEntity entity = toEntity(itemDTO);
            ItemCarritoEntity guardado = carritoRepository.save(entity);
            return toDTO(guardado);
        }
    }
    
    public boolean eliminarItem(Long itemId) {
        if (carritoRepository.existsById(itemId)) {
            carritoRepository.deleteById(itemId);
            return true;
        }
        return false; 
    }

    // --- Conversiones ---

    private ItemCarritoDTO toDTO(ItemCarritoEntity entity) {
        ItemCarritoDTO dto = new ItemCarritoDTO();
        dto.setId(entity.getId());
        dto.setProductoId(entity.getProductoId());
        dto.setUsuarioId(entity.getUsuarioId());
        dto.setCantidad(entity.getCantidad());
        return dto;
    }

    private ItemCarritoEntity toEntity(ItemCarritoDTO dto) {
        ItemCarritoEntity entity = new ItemCarritoEntity();
        // 'id' se ignora para creación
        entity.setProductoId(dto.getProductoId());
        entity.setUsuarioId(dto.getUsuarioId());
        entity.setCantidad(dto.getCantidad());
        return entity;
    }
}