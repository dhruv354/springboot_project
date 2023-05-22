FROM openjdk:11
VOLUME /tmpfiles
EXPOSE 8080
ARG JAR_FILE=target/demo-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} project.jar
ENTRYPOINT ["java","-jar","/project.jar"]