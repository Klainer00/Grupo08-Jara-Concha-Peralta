package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder; 
import org.springframework.stereotype.Service;
import modelo.Usuario;
import modelo.Rol; 
import repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; 

    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public Usuario crearUsuario(Usuario usuario) {
        if (usuarioRepository.findByCorreo(usuario.getCorreo()).isPresent()) {

            throw new RuntimeException("El correo ya está en uso"); 
        }

        usuario.setContraseña(passwordEncoder.encode(usuario.getContraseña()));
        
        if (usuario.getRole() == null) {
            usuario.setRole(Rol.USER);
        }
        
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> actualizarUsuario(Long id, Usuario usuarioDetalles) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);

        if (usuarioOpt.isEmpty()) {
            return Optional.empty();
        }

        Usuario usuarioExistente = usuarioOpt.get();
        usuarioExistente.setNombre(usuarioDetalles.getNombre());
        usuarioExistente.setCorreo(usuarioDetalles.getCorreo());
        
        if (usuarioDetalles.getContraseña() != null && !usuarioDetalles.getContraseña().isEmpty()) {
            usuarioExistente.setContraseña(passwordEncoder.encode(usuarioDetalles.getContraseña()));
        }
        
        usuarioExistente.setDireccion(usuarioDetalles.getDireccion());
        usuarioExistente.setTelefono(usuarioDetalles.getTelefono());
        return Optional.of(usuarioRepository.save(usuarioExistente));
    }

    public boolean borrarUsuario(Long id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return true;
        }
        return false; 
    }
}