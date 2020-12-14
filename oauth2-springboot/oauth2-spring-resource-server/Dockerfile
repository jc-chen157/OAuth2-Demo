FROM adoptopenjdk/openjdk11:jdk-11.0.7_10-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENV JAVA_OPTS = "-Xmx 1024 -agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=5005"
EXPOSE 8090 5005
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=docker", "/app.jar"]