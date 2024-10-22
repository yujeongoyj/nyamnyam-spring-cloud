FROM openjdk:17-jdk-slim
VOLUME /tmp
RUN apt-get update && apt-get install -y curl
COPY ./build/libs/admin-service-0.0.1-SNAPSHOT.jar admin-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/admin-service-0.0.1-SNAPSHOT.jar"]