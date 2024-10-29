FROM openjdk:21
WORKDIR /app
COPY target/sistema-reservas-salas-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "reserva-salas.jar"]
