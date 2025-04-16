# --- Build Stage ---
FROM maven:3.9.7-eclipse-temurin-22-alpine AS build

WORKDIR /app

# Copy pom and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the rest of the source code
COPY src ./src

# Package the app (skip checkstyle)
RUN mvn clean package -Dcheckstyle.skip=true

# --- Runtime Stage ---
FROM maven:3.9.7-eclipse-temurin-22-alpine

WORKDIR /app

# Copy built jar from previous stage
COPY --from=build /app/target/*.jar app.jar

# Copy the uploads directory to the root of the app
COPY uploads /app/uploads

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
