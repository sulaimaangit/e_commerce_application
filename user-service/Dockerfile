FROM gradle:9.9-jdk21 AS build
WORKDIR /workspace

COPY settings.gradle build.gradle ./
COPY product-service/build.gradle product-service/build.gradle
COPY user-service/build.gradle user-service/build.gradle
COPY sales-service/build.gradle sales-service/build.gradle
COPY user-service/src user-service/src

RUN gradle :user-service:bootJar --no-daemon

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /workspace/user-service/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
