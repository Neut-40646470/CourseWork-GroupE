# Use an OpenJDK runtime as base image
FROM openjdk:10 AS builder

# Set the working directory in the container
WORKDIR /app

# Copy the packaged JAR file from the target directory to the container
COPY target/seMethods-0.1.jar /app/seMethods.jar

# Use an OpenJDK runtime as final base image
FROM openjdk:10

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file from the builder stage to the final container
COPY --from=builder /app/seMethods.jar /app/seMethods.jar

# Starting command to run the application
ENTRYPOINT ["java", "-cp", "seMethods.jar", "com.napier.sem.App"]
