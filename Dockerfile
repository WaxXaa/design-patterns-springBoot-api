FROM openjdk:20

WORKDIR /app

COPY gradlew /app/
COPY gradle /app/gradle
COPY build.gradle /app/
COPY settings.gradle /app/

COPY src /app/src

RUN ./gradlew clean build

COPY build/libs/DesignPatterns-Game-0.0.1-SNAPSHOT.jar /app/app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/tu-proyecto.jar"]
