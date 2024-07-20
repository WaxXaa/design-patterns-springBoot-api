FROM openjdk:20

WORKDIR /app

RUN ./gradlew clean build

COPY build/libs/DesignPatterns-Game-0.0.1-SNAPSHOT.jar /app/app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/tu-proyecto.jar"]
