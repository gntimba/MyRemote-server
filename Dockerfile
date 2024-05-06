FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/*.jar MyRemote-1.2.1.jar
ENTRYPOINT ["java","-jar","/MyRemote-1.2.1.jar"]
