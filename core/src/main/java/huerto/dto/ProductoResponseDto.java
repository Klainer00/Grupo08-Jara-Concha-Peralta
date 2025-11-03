package huerto.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder // Usamos builder para facilitar la conversión
public class ProductoResponseDto {
    private Long id;
    private String nombre;
    private String descripcion;
    private Double precio;
    private Integer stock;
    private String categoriaNombre; // Devolvemos solo el nombre de la categoría
}