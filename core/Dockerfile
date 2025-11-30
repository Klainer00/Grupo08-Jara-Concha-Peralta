# --- Etapa 1: Build ---
FROM maven:3.9-eclipse-temurin-17 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# --- Etapa 2: Runtime ---
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar

# Puerto 8182 para el Core (según tu application.properties)
EXPOSE 8182

ENTRYPOINT ["java", "-jar", "app.jar"]
