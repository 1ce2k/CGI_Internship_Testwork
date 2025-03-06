FROM gradle:8.7-jdk-21-and-22 AS BUILD
WORKDIR /app
copy . .
RUN gradle clean bootjar --no-daemon

FROM eclipse-temurin:23-jre-alpine
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]