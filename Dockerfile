FROM openjdk:11
EXPOSE 8092
COPY target/my-veterinary-ms-1.0-SNAPSHOT.jar my-veterinary-ms-1.0-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/my-veterinary-ms-1.0-SNAPSHOT.jar"]