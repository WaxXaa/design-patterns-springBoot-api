FROM openjdk:20

WORKDIR /app

RUN ./gradlew clean build

COPY build/libs/DesignPatterns-Game-*.jar /app/app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
