FROM openjdk:17-jdk-slim
VOLUME /tmp
RUN apt-get update && apt-get install -y curl
COPY ./build/libs/user-service-0.0.1-SNAPSHOT.jar admin-service.jar
ENTRYPOINT ["java", "-jar", "/admin-service.jar"]