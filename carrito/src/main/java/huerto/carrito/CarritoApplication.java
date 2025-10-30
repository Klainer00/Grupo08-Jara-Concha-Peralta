package huerto.carrito;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan; // Importar
import org.springframework.context.annotation.ComponentScan; // Importar
import org.springframework.data.jpa.repository.config.EnableJpaRepositories; // Importar

@SpringBootApplication
@ComponentScan(basePackages = {"huerto.carrito", "config", "repository", "modelo"})
@EnableJpaRepositories(basePackages = "repository")
@EntityScan(basePackages = "modelo")
public class CarritoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarritoApplication.class, args);
	}

}