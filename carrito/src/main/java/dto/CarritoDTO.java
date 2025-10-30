package huerto.carrito.dto;

import lombok.Data;

@Data
public class ItemCarritoDTO {
    private Long id;
    private Long productoId;
    private Long usuarioId;
    private int cantidad;
}

// Podríamos tener DTOs más específicos si fuese necesario,
// por ej: ActualizarCantidadDTO { int cantidad; }