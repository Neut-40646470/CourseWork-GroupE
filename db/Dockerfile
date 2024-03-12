# Use the latest MySQL image
FROM mysql

# Copy the SQL script to the appropriate MySQL initialization directory
COPY world.sql /docker-entrypoint-initdb.d/

# Set the root password
ENV MYSQL_ROOT_PASSWORD 123