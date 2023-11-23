FROM openjdk:17

WORKDIR /app
COPY ./artifact/movieapi.jar /app
COPY ./application.properties /app

EXPOSE 8080

CMD ["java", "-jar", "movieapi.jar"]