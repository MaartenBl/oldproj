FROM openjdk:8-jre-alpine
EXPOSE 8089
WORKDIR /app
COPY target/user-service-0.0.1-SNAPSHOT.jar .
ENTRYPOINT [ "java", "-jar", "user-service-0.0.1-SNAPSHOT.jar" ]
