FROM maven:3.9.3-eclipse-temurin-17-focal AS builder

RUN apt-get update \
  && apt-get install -y ca-certificates curl git gnupg dirmngr wget --no-install-recommends \
  && rm -rf /var/lib/apt/lists/*

WORKDIR /meucafofo
RUN git clone https://github.com/meucafofo/projeto-backend.git

WORKDIR /meucafofo/projeto-backend
RUN mvn clean package -Dmaven.test.skip=true

# Limpar depedencias
RUN rm -rf ~/.m2

FROM openjdk:17 AS app

WORKDIR /meucafofo
COPY --from=builder /meucafofo/projeto-backend/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","/meucafofo/app.jar"]