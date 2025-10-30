package huerto.carrito.repository;

import huerto.carrito.modelo.ItemCarrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional; // Importa Optional

@Repository
public interface CarritoRepository extends JpaRepository<ItemCarrito, Long> {
    
    // Método para buscar todos los items de un usuario
    List<ItemCarrito> findByUsuarioId(Long usuarioId);


    Optional<ItemCarrito> findByUsuarioIdAndProductoId(Long usuarioId, Long productoId);
}