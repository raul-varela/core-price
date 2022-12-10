FROM maven:3.6.0-jdk-11-slim AS build
COPY . /home/app/
COPY pom.xml /home/app

COPY pom.xml /home/app/pom.xml
COPY core-price-api/pom.xml /home/app/core-price-api/pom.xml
COPY core-price-domain/pom.xml /home/app/core-price-domain/pom.xml
COPY core-price-security/pom.xml /home/app/core-price-security/pom.xml
COPY core-price-util/pom.xml /home/app/core-price-util/pom.xml

RUN mvn -f /home/app/pom.xml clean package


FROM openjdk:12-alpine
ENV TZ=America/Argentina/Mendoza
ENV TERM=xterm
RUN mkdir -p /app
COPY --from=build /home/app/core-price-api/target/core-price-api.jar /app/challenge.jar


WORKDIR /app
ENV VIRTUAL_HOST=challenge
ENV VIRTUAL_PORT=8080
EXPOSE ${VIRTUAL_PORT}/tcp
ENV spring.application.name=challenge
ENTRYPOINT ["java","-jar","/app/challenge.jar","--server.port=${VIRTUAL_PORT}"]