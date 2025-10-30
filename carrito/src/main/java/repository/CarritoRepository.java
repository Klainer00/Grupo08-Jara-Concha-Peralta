package huerto.carrito.repository;

import huerto.carrito.entity.ItemCarritoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional; 

@Repository
public interface CarritoRepository extends JpaRepository<ItemCarritoEntity, Long> { z
    
    List<ItemCarritoEntity> findByUsuarioId(Long usuarioId); 

    Optional<ItemCarritoEntity> findByUsuarioIdAndProductoId(Long usuarioId, Long productoId); 
}