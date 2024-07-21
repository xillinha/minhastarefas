#
# Package stage
#
FROM amazoncorretto:17.0.12
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/minhastarefas-0.0.1-SNAPSHOT.jar"]