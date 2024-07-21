FROM openjdk:20




ARG JAR_FILE
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]










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
