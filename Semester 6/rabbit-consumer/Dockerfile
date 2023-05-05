FROM maven:3.6.3-openjdk-8
EXPOSE 9797
COPY pom.xml /tmp/pom.xml
RUN mvn "-f" "/tmp/pom.xml" "clean" "install"
