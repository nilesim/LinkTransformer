FROM openjdk:8-jdk-alpine
EXPOSE 8080
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} linktransformer.jar
ENTRYPOINT ["java","-jar","/linktransformer.jar"]