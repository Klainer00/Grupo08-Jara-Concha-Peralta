



package modelo;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnore; // <--- AGREGAR ESTO

@Data
@Entity
@Table(name = "roles")
public class RolEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombre; 

    @OneToMany(mappedBy = "role")
    @JsonIgnore  // <--- AGREGAR ESTO (Rompe el bucle infinito)
    private Set<Usuario> usuarios;
}
