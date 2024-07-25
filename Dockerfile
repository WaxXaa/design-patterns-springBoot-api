#FROM openjdk:20
#
#
#
#
#ARG JAR_FILE
#COPY ${JAR_FILE} app.jar
#ENTRYPOINT ["java", "-jar", "/app.jar"]
#
#
#
#






#COPY gradlew /app/
#COPY gradle /app/gradle
#COPY build.gradle /app/
#COPY settings.gradle /app/
#COPY src /app/src
#
#
#RUN ./gradlew clean build
#
#COPY build/libs/DesignPatterns-Game-*.jar /app/app.jar
#
#
#
#CMD ["java", "-jar", "/app/app.jar"]









FROM gradle:jdk20 AS build
WORKDIR /app
COPY . .
RUN gradle clean build

FROM openjdk:20
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]


















#
#FROM ubuntu:latest AS build
#
#RUN apt-get update
#RUN apt-get install openjdk-20-jdk -y
#COPY . .
#
#RUN ./gradlew clean build --no-daemon
#
#FROM openjdk:17-jdk-slim
#
#EXPOSE 8080
#
#COPY --from=build build/libs/DesignPatterns-Game-0.0.1-SNAPSHOT.jar app.jar
#
#ENTRYPOINT ["java", "-jar", "app.jar"]