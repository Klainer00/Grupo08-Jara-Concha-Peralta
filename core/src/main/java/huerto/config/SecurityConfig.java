package main.java.huerto.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.http.HttpMethod;

@Configuration
public class SecurityConfig {

    private final org.springframework.security.authentication.AuthenticationProvider authenticationProvider;

    public SecurityConfig(org.springframework.security.authentication.AuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) 
            .authorizeHttpRequests(auth -> auth
                // PERMITIMOS ver productos y categorías a CUALQUIERA
                .requestMatchers(HttpMethod.GET, "/api/productos/**", "/api/categorias/**").permitAll() 
                
                // El resto (POST, PUT, DELETE) requiere autenticación
                .anyRequest().authenticated() 
            )
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authenticationProvider(authenticationProvider)
            .httpBasic(httpBasic -> {}); 

        return http.build();
    }
}