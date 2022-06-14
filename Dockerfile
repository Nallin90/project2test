FROM openjdk:8
EXPOSE 8085
ADD target/project2.jar app.jar
ENTRYPOINT [ "java" , "-jar" , "/app.jar"]