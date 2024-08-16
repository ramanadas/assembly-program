# Stage 1: Prepare dependencies
FROM maven:3.8.4-openjdk-17 AS dependencies
WORKDIR /assembly

# Copy only the pom.xml to leverage Docker layer caching
COPY pom.xml .
COPY src ./src

# Download dependencies
RUN mvn dependency:go-offline

# Stage 2: Build the application
FROM dependencies AS builder

# Build the application
RUN mvn package -DskipTests

# Stage 3: Create the final image
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copy the built JAR from the builder stage
COPY --from=builder /assembly/target/assembly-0.0.1-SNAPSHOT.jar ./assembly.jar

# Expose the application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "assembly.jar"]
