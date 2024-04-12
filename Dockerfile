# Use an OpenJDK image as base image
FROM openjdk:latest
COPY ./target/seMethods-0.1-jar-with-dependencies.jar /tmp
WORKDIR /tmp
# Starting command to run the application
CMD ["java", "-jar", "seMethods-0.1-jar-with-dependencies.jar"]
