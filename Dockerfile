FROM openjdk:8-jdk-alpine
VOLUME ["/tmp"]
EXPOSE 8080
ARG JAR_FILE
COPY hoszn-v1.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]