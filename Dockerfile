FROM openjdk:20

#COPY gradlew /app/
#COPY gradle /app/gradle
#COPY build.gradle /app/
#COPY settings.gradle /app/
#COPY src /app/src
#
#
#RUN ./gradlew clean build

COPY build/libs/DesignPatterns-Game-*.jar /app/app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
