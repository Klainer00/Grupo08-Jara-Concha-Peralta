package huerto.carrito.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class ItemCarrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Podrías necesitar el ID del producto (que vendrá de otro microservicio)
    private Long productoId;
    
    // Y el ID del usuario al que pertenece este carrito
    private Long usuarioId;

    private int cantidad;
    
    // Otros campos que necesites...
}