# runtime-only Dockerfile (use when you build jar on host)
FROM eclipse-temurin:17-jdk
WORKDIR /app

# copy whatever jar Maven produced
COPY target/*.jar app.jar

COPY src/main/resources/application-prod.properties /app/config/application-prod.properties
COPY .env /app/.env

ENV SPRING_CONFIG_LOCATION=/app/config/application-prod.properties
ENV SPRING_PROFILES_ACTIVE=prod

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
