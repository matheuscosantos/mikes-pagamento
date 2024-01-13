FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY build/libs/mikes-payment.jar mikes-payment.jar

EXPOSE 8050

ENTRYPOINT ["java", "-jar", "mikes-payment.jar"]