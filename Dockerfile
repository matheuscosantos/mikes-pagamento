FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY build/libs/mikes_payment.jar mikes_payment.jar

EXPOSE 8050

ENTRYPOINT ["java", "-jar", "mikes_payment.jar"]