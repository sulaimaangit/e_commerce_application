FROM gradle:9.9-jdk21 AS build
WORKDIR /workspace

COPY settings.gradle build.gradle ./
COPY product-service/build.gradle product-service/build.gradle
COPY user-service/build.gradle user-service/build.gradle
COPY sales-service/build.gradle sales-service/build.gradle
COPY product-service/src product-service/src

RUN gradle :product-service:bootJar --no-daemon

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /workspace/product-service/build/libs/*.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
