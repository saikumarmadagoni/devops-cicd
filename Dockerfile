FROM openjdk:17
WORKDIR /app

COPY ./application.jar /app
EXPOSE 8080
CMD ["java", "-jar", "application.jar"]
