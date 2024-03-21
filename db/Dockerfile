# Use the latest MySQL image
FROM mysql
WORKDIR /tmp

#COPY *.sql /tmp/
COPY world.sql /tmp/

# Copy the SQL script to the appropriate MySQL initialization directory
COPY world.sql /docker-entrypoint-initdb.d

# Set the root password
ENV MYSQL_ROOT_PASSWORD 123