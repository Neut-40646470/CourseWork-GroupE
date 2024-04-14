FROM openjdk:latest
WORKDIR /app
COPY ./target/seMethods.jar /app
# Copy the entire project directory (ensure it includes 'src/main/resources/')
COPY . /app
ENTRYPOINT ["java", "-jar", "seMethods.jar", "db:3306", "10000"]

