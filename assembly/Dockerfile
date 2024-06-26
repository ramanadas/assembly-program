## Stage 1: Build the application
#FROM maven:3.8.4-openjdk-17 AS builder
#WORKDIR /assembly
#
## Copy the pom.xml and download dependencies
#COPY pom.xml .
#RUN mvn dependency:go-offline -B
#
## Copy the source code and build the application
#COPY src ./src
#RUN mvn clean package -DskipTests

# Stage 2: Create the final image
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copy the built jar from the builder stage
COPY ./target/assembly-0.0.1-SNAPSHOT.jar ./assembly.jar

# Expose the application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "assembly.jar"]
