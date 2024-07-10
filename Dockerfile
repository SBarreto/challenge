FROM maven:3-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jdk-alpine
COPY --from=build /target/challenge-0.0.1-SNAPSHOT.jar challenge.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "challenge.jar"]