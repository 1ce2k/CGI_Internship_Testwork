# Build stage
FROM gradle:8.7-jdk21 AS build
WORKDIR /app
COPY . .
# Add memory settings that work on both platforms
ENV GRADLE_OPTS="-Dorg.gradle.daemon=false -Dorg.gradle.parallel=true -Xmx2g"
RUN gradle clean bootJar --no-daemon --stacktrace

# Run stage
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-XX:+UseContainerSupport", "-XX:MaxRAMPercentage=75.0", "-jar", "app.jar"]