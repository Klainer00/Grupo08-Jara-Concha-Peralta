package huerto.carrito.dto;

import lombok.Data;

@Data
public class ItemCarritoDTO {
    private Long id;
    private Long productoId;
    private Long usuarioId;
    private int cantidad;
}


