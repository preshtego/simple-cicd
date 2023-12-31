#FROM java:8
FROM adoptopenjdk/openjdk11:alpine-jre
LABEL maintainer="info@presh.com"
WORKDIR /myapp
COPY target/hello-world-cicd-0.0.1-SNAPSHOT.jar /myapp/my-app.jar
ENTRYPOINT ["java","-jar","my-app.jar"]
