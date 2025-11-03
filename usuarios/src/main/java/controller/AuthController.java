package controller;

import lombok.RequiredArgsConstructor;
import modelo.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qingzhuge.framework.security.dto.RegisterRequestDTO;

import dto.RegisterRequestDto;
import dto.UsuarioResponseDto;
import service.UsuarioService;
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UsuarioService usuarioService;

    @PostMapping("/register")
    // 1. Usar DTOs para request y response
    public ResponseEntity<dto.UsuarioResponseDto> register(@RequestBody RegisterRequestDto requestDto) {
        try {
            // 2. El servicio ahora recibe el DTO
            Usuario nuevoUsuario = usuarioService.crearUsuario(requestDto); 
            
            // 3. Convertir la entidad a DTO de respuesta (sin contraseña)
            dto.UsuarioResponseDto responseDto = dto.UsuarioResponseDto.builder()
                .id(nuevoUsuario.getId())
                .nombre(nuevoUsuario.getNombre())
                .correo(nuevoUsuario.getCorreo())
                .direccion(nuevoUsuario.getDireccion())
                .telefono(nuevoUsuario.getTelefono())
                .rol(nuevoUsuario.getRole().getNombre())
                .build();
            
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
        } catch (RuntimeException e) {
            // Puedes manejar mejor la excepción si "El correo ya está en uso"
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }
}