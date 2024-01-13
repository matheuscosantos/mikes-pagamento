FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY build/libs/payment.jar payment.jar

EXPOSE 8050

ENTRYPOINT ["java", "-jar", "payment.jar"]