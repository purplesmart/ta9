FROM openjdk:20
LABEL authors="davidilous"
WORKDIR /app
COPY target/TA9-1.0-SNAPSHOT.jar /app/TA9-1.0-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar","TA9-1.0-SNAPSHOT.jar"]