Claro, aquí tienes la documentación para el README.md del repositorio del Grupo 08, siguiendo el formato del ejemplo proporcionado.

Huerto Hogar – Microservicios
Este proyecto está hecho en el entorno Spring Tools Suite en Spring Boot. Cada servicio tiene sus correspondientes endpoints, y acá se detalla la forma de probar cada microservicio.

EQUIPO 8:

Jara

Concha

Peralta

1. Usuario Microservice
Archivo: usuarios

Puerto: 8180

Nombre interno: usuarios

Endpoints principales

POST - http://localhost:8180/api/auth/register

Registrar nuevo usuario

JSON

{
  "nombre": "Nombre Apellido",
  "email": "user@example.com",
  "password": "1234",
  "rol": "USER"
}
GET - http://localhost:8180/api/usuarios

Obtener todos los usuarios (Solo ADMIN)

GET - http://localhost:8180/api/usuarios/{id}

Obtener usuario por ID (Solo ADMIN)

PUT - http://localhost:8180/api/usuarios/{id}

Actualizar usuario (Solo ADMIN)

JSON

{
  "nombre": "Nuevo Nombre"
}
DELETE - http://localhost:8180/api/usuarios/{id}

Eliminar usuario (Solo ADMIN)

2. Core Microservice (Productos)
Archivo: core

Puerto: 8080 (default, no especificado en properties)

Nombre interno: core

Endpoints principales

GET - http://localhost:8080/api/productos

Obtener todos los productos

POST - http://localhost:8080/api/productos

Crear producto nuevo (Solo ADMIN)

JSON

{
  "nombre": "Tomate",
  "precio": 1500,
  "stock": 100,
  "categoria": "Hortalizas"
}
3. Carrito Microservice
Archivo: carrito

Puerto: 8180

Nombre interno: carrito

Endpoints principales

GET - http://localhost:8180/api/carrito

Obtener carrito (Solo USER o ADMIN)

POST - http://localhost:8180/api/carrito/agregar

Agregar producto al carrito (Solo USER)

JSON

{
  "Idusuario": 1,
  "Idproducto": 2,
  "cantidad": 3
}
