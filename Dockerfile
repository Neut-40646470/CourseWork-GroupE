                                                  
# Stage 1: Build stage
FROM openjdk:17 as builder

WORKDIR /app

# Copy source code and compile
COPY src/main/java /app/src
RUN javac -d /app /app/src/com/napier/sem/*.java

# Create executable jar
WORKDIR /app
RUN jar cvfe seMethods.jar com.napier.sem.Main -C /app .

# Download mysql connector
RUN curl -o /tmp/mysql-connector-java.jar https://repo1.maven.org/maven2/mysql/mysql-connector-java/8.0.28/mysql-connector-java-8.0.28.jar

# Stage 2: Runtime stage
FROM openjdk:17

WORKDIR /tmp

# Copy the jar files from the builder
COPY --from=builder /app/seMethods.jar /tmp
COPY --from=builder /tmp/mysql-connector-java.jar /tmp

# Set classpath
ENV CLASSPATH="/tmp/seMethods.jar:/tmp/mysql-connector-java.jar"

# Create directory with proper permissions in /tmp
RUN mkdir -p /tmp/reports && chmod 777 /tmp/reports

# Command to run the application
CMD ["java", "com.napier.sem.Main"]