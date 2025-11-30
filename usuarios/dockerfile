# --- ETAPA 1: BUILD (Compilación) ---
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app

# Copiamos primero el pom.xml para aprovechar la caché de capas de Docker
COPY pom.xml .
# Descargamos dependencias (esto hace que la build sea más rápida en el futuro)
RUN mvn dependency:go-offline -B

# Copiamos el código fuente
COPY src ./src

# Compilamos el jar saltando los tests para agilizar
RUN mvn clean package -DskipTests

# --- ETAPA 2: RUN (Ejecución) ---
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copiamos el JAR generado en la etapa anterior
COPY --from=build /app/target/*.jar app.jar

# Ejecutamos la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]