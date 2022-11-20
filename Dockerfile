FROM openjdk:17-alpine
WORKDIR /app
COPY build/libs/appointmentalert-0.1-all.jar /app/application.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/application.jar"]
