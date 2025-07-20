FROM openjdk:17-jdk-slim

COPY target/api-gateway.jar api-gateway.jar

ENTRYPOINT ["java", "-jar", "/api-gateway.jar"]