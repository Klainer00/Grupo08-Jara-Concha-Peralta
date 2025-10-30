package huerto.carrito.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table; 
import jakarta.persistence.Column; 
import jakarta.persistence.UniqueConstraint; 
import lombok.Data;

@Data
@Entity
@Table(name = "items_carrito", // 1. Igual que en Usuario, especificamos el nombre de la tabla
       uniqueConstraints = {
           // 2. Esta es la validación MÁS importante:
           // Asegura que no pueda haber dos filas con el mismo usuarioId y productoId.
           // Es la versión en BBDD de la lógica que hicimos en el Service.
           @UniqueConstraint(columnNames = {"usuarioId", "productoId"})
       }
)
public class ItemCarrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 3. Igual que en Usuario, marcamos como no-nulo
    @Column(nullable = false)
    private Long productoId;
    
    @Column(nullable = false)
    private Long usuarioId;

    @Column(nullable = false)
    private int cantidad;
}