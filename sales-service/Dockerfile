FROM gradle:9.9-jdk21 AS build
WORKDIR /workspace

COPY settings.gradle build.gradle ./
COPY product-service/build.gradle product-service/build.gradle
COPY user-service/build.gradle user-service/build.gradle
COPY sales-service/build.gradle sales-service/build.gradle
COPY sales-service/src sales-service/src

RUN gradle :sales-service:bootJar --no-daemon

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /workspace/sales-service/build/libs/*.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
