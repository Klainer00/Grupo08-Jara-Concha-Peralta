package huerto.core.repository;

import huerto.core.entity.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<ProductoEntity, Long> {
    // Query objetal
    List<ProductoEntity> findByCategoriaId(Long categoriaId);
}