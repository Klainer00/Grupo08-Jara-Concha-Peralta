package huerto.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // Desactivar CSRF para permitir POST desde Postman
            .csrf(csrf -> csrf.disable())
            // Permitir TODAS las peticiones sin login
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll()
            )
            // Desactivar formularios de login por defecto
            .httpBasic(basic -> basic.disable())
            .formLogin(form -> form.disable());

        return http.build();
    }
}
