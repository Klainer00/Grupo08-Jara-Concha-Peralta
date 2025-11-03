package huerto.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.http.HttpMethod; // Importante

@Configuration
@EnableWebSecurity
@EnableMethodSecurity // Esto es VITAL para que @PreAuthorize("hasAuthority('ADMIN')") funcione
public class SecurityConfig {

    // NO inyectamos el AuthenticationProvider, para que Spring
    // use el que definimos en application.properties.

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) 
            .authorizeHttpRequests(auth -> auth
                
                // Permite endpoints públicos (GET para ver productos)
                .requestMatchers(HttpMethod.GET, "/api/productos", "/api/productos/**").permitAll() 
                
                // Requiere autenticación para todo lo demás
                .anyRequest().authenticated() 
            )
            .sessionManagement(session -> session
               
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) 
            )
            // NO añadimos .authenticationProvider()
            .httpBasic(httpBasic -> {}); // Usar Basic Auth

        return http.build();
    }
}