package huerto.carrito.entity; // Paquete cambiado

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "items_carrito",
       uniqueConstraints = {
           @UniqueConstraint(columnNames = {"usuarioId", "productoId"})
       }
)
public class ItemCarritoEntity { // Nombre cambiado

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long productoId;
    
    @Column(nullable = false)
    private Long usuarioId;

    @Column(nullable = false)
    private int cantidad;
}