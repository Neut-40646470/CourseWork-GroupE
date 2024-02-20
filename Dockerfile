# Use an OpenJDK image as base image
FROM openjdk:17

# Set the working directory in the container
WORKDIR /app

# Copy the built JAR file from the target directory to the container
COPY target/seMethods-0.1.jar /app/seMethods.jar

# Starting command to run the application
CMD ["java", "-jar", "seMethods.jar", "com.napier.sem.Main"]
