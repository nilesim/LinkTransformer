FROM library/postgres
COPY /docker-entrypoint-initdb.d/ init-user-db.sh

FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/linktransformer.jar"]