FROM openjdk:17-jdk-alpine

WORKDIR /app

RUN apk update && apk add --no-cache curl

COPY build/libs/payment.jar payment.jar

EXPOSE 8050

ENTRYPOINT ["java", "-jar", "payment.jar"]