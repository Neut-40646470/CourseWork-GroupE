# Use an OpenJDK image as base
FROM openjdk:latest

# Create a directory for your application
WORKDIR /app

# Copy the jar file and resource files into the container
COPY ./target/seMethods-0.1-jar-with-dependencies.jar /app
COPY ./src/main/resources/ /app/resources

# Starting command to run the application
CMD ["java", "-jar", "seMethods-0.1-jar-with-dependencies.jar", "db:3306", "30000"]

