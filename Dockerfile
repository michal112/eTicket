FROM openjdk:latest
WORKDIR app
COPY target/eTicket.jar .
CMD ["java", "-jar", "eTicket.jar"]