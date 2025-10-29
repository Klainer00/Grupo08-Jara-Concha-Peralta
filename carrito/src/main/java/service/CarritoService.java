package huerto.carrito.service;

import huerto.carrito.modelo.ItemCarrito;
import huerto.carrito.repository.CarritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarritoService {

    @Autowired
    private CarritoRepository carritoRepository;

    public List<ItemCarrito> verCarrito(Long usuarioId) {
        return carritoRepository.findByUsuarioId(usuarioId);
    }

    public ItemCarrito agregarItem(ItemCarrito item) {
        // Aquí podrías añadir lógica para verificar si el producto ya existe
        // y en ese caso, solo sumar la cantidad.
        return carritoRepository.save(item);
    }

    public void eliminarItem(Long itemId) {
        carritoRepository.deleteById(itemId);
    }
    
    // Otros métodos que necesites...
}