package huerto.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
// Escanea todos los paquetes que creamos (controlador, servicio, etc.)
@ComponentScan(basePackages = {"huerto.controller", "huerto.service", "huerto.config"})
// Apunta a dónde están los repositorios
@EnableJpaRepositories(basePackages = "huerto.repository")
// Apunta a dónde están las entidades (Producto, CategoriaEntity)
@EntityScan(basePackages = "huerto.model")
public class CoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoreApplication.class, args);
	}

}