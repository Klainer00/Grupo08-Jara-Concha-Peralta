package huerto.carrito;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// Eliminamos las importaciones de JPA
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
// CORRECCIÓN: Añadido "huerto.controller" al ComponentScan
@ComponentScan(basePackages = {"huerto.carrito", "huerto.controller"})
// Anotaciones @EnableJpaRepositories y @EntityScan eliminadas
public class CarritoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarritoApplication.class, args);
	}

}