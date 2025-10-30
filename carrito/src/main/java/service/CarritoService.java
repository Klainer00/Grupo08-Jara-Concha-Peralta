package huerto.carrito.service;

import huerto.carrito.modelo.ItemCarrito;
import huerto.carrito.repository.CarritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarritoService {

    @Autowired
    private CarritoRepository carritoRepository;

    /**
     * Obtiene todos los items del carrito para un usuario.
     * No requiere validación de existencia, si no hay items, devuelve lista vacía.
     */
    public List<ItemCarrito> verCarrito(Long usuarioId) {
        return carritoRepository.findByUsuarioId(usuarioId);
    }

    /**
     * Agrega un item al carrito.
     * VALIDACIÓN: Similar a 'crearUsuario', comprueba si el item ya existe.
     * Si ya existe, actualiza la cantidad en lugar de crear un duplicado.
     */
    public ItemCarrito agregarItem(ItemCarrito nuevoItem) {
        if (nuevoItem.getUsuarioId() == null || nuevoItem.getProductoId() == null) {
            throw new RuntimeException("ID de usuario y ID de producto no pueden ser nulos");
        }

        // 1. Validar si el item ya existe (similar al findByCorreo en UsuarioService)
        Optional<ItemCarrito> itemExistenteOpt = carritoRepository.findByUsuarioIdAndProductoId(
                nuevoItem.getUsuarioId(),
                nuevoItem.getProductoId()
        );

        if (itemExistenteOpt.isPresent()) {
            // Si existe, actualizamos la cantidad del item existente
            ItemCarrito itemExistente = itemExistenteOpt.get();
            itemExistente.setCantidad(itemExistente.getCantidad() + nuevoItem.getCantidad());
            return carritoRepository.save(itemExistente);
        } else {
            // Si no existe, lo creamos
            // Aseguramos que la cantidad sea al menos 1
            if (nuevoItem.getCantidad() <= 0) {
                nuevoItem.setCantidad(1);
            }
            return carritoRepository.save(nuevoItem);
        }
    }

    /**
     * Actualiza la cantidad de un item específico.
     * VALIDACIÓN: Similar a 'actualizarUsuario', comprueba que el item exista.
     */
    public Optional<ItemCarrito> actualizarCantidadItem(Long itemId, int nuevaCantidad) {
        // 1. Validar si existe (similar a actualizarUsuario)
        Optional<ItemCarrito> itemOpt = carritoRepository.findById(itemId);

        if (itemOpt.isEmpty()) {
            return Optional.empty(); // No se encontró el item
        }

        ItemCarrito item = itemOpt.get();

        // Regla de negocio: si la cantidad es 0 o menos, eliminamos el item
        if (nuevaCantidad <= 0) {
            carritoRepository.delete(item);
            return Optional.empty(); // Se borró, así que ya no existe
        } else {
            // Actualizamos la cantidad y guardamos
            item.setCantidad(nuevaCantidad);
            return Optional.of(carritoRepository.save(item));
        }
    }


    public boolean eliminarItem(Long itemId) {
        if (carritoRepository.existsById(itemId)) {
            carritoRepository.deleteById(itemId);
            return true;
        }
        return false; // No se encontró el item
    }
}