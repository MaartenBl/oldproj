FROM maven:3.6.1-jdk-8-alpine AS MAVEN_BUILD
COPY ./ ./
RUN mvn clean package
RUN ls MAVEN_BUILD
#FROM openjdk:8-jre-alpine3.9
#COPY --from=MAVEN_BUILD nuclear-service/target/nuclear-service.0.0.1-SNAPSHOT.jar /nuclear-service.jar
#CMD ["java", "-jar", "target/nuclear-service.jar"]
