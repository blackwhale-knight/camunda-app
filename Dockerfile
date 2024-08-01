FROM openjdk:21-slim
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
RUN rm -rf /logs
RUN mkdir /logs
ENTRYPOINT ["java", "-jar", "/app.jar"]
VOLUME /logs