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










#
## Usar una imagen base con JDK 8 y Gradle
FROM gradle:8.8-jdk17 AS build
#
## Establecer un directorio de trabajo
WORKDIR /app
#
## Copiar archivos de tu proyecto al directorio de trabajo
COPY . /app
#
## Ejecutar Gradle para construir el proyecto
RUN gradle clean build --no-daemon --stacktrace
#
## Crear una nueva imagen basada en OpenJDK 8
FROM openjdk:20
#
## Exponer el puerto que utilizará la aplicación
EXPOSE 8080
#
## Copiar el archivo JAR construido desde la etapa anterior
COPY --from=build build/libs/DesignPatterns-Game-0.0.1-SNAPSHOT.jar /app/DB-0.0.1-SNAPSHOT.jar
#
## Establecer el punto de entrada para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app/DB-0.0.1-SNAPSHOT.jar"]
#
#




















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