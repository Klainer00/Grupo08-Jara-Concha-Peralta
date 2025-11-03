package huerto.dto;

import lombok.Data;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Data
public class ProductoRequestDto {
    
    @NotNull(message = "El nombre no puede ser nulo")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    private String nombre;
    
    private String descripcion;
    
    @NotNull(message = "El precio no puede ser nulo")
    @Positive(message = "El precio debe ser un número positivo")
    private Double precio;
    
    @NotNull(message = "El stock no puede ser nulo")
    @Positive(message = "El stock debe ser un número positivo")
    private Integer stock;
    
    @NotNull(message = "El ID de la categoría no puede ser nulo")
    private Long categoriaId; // Solo pedimos el ID de la categoría
}