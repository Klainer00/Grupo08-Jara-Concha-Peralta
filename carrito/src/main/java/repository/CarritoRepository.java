package huerto.carrito.repository;

import huerto.carrito.modelo.ItemCarrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarritoRepository extends JpaRepository<ItemCarrito, Long> {
    
    // Método útil para buscar todos los items de un usuario
    List<ItemCarrito> findByUsuarioId(Long usuarioId);
}