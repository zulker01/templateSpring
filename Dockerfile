# Use OpenJDK 21 as the base image
FROM openjdk:21-slim

# Set the working directory in the container
WORKDIR /app

# Copy the built JAR file into the container's working directory
COPY build/libs/template_spring-0.0.1-SNAPSHOT.jar app.jar

# Expose the port that your Spring Boot app will run on (default is 8080)
EXPOSE 4969

# Run the JAR file using the `java -jar` command
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
