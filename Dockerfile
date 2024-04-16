#get jdk base image, allows running of java applications
FROM openjdk:latest
#copy generated jar into container file system
COPY ./target/seMethods.jar /tmp
#set working directory of container
WORKDIR /tmp
#runs java program on start of container
ENTRYPOINT ["java", "-jar", "seMethods.jar", "db:3306"]