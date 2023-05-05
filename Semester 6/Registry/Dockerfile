FROM maven:3.6.3-openjdk-8
COPY pom.xml /tmp/pom.xml
RUN mvn "-B" "-f" "/tmp/pom.xml" "-s" "/usr/share/maven/ref/settings-docker.xml" "dependency:resolve"
