# --- Etapa 1: Build (Compilación) ---
FROM maven:3.9-eclipse-temurin-17 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
# Empaquetamos saltando tests para agilizar
RUN mvn clean package -DskipTests

# --- Etapa 2: Runtime (Ejecución ligera) ---
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
# Copiamos solo el JAR compilado
COPY --from=builder /app/target/*.jar app.jar

# Puerto 8180 para usuarios
EXPOSE 8180

ENTRYPOINT ["java", "-jar", "app.jar"]
