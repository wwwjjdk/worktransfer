FROM openjdk:18-jdk-alpine

EXPOSE 8080

ADD target/CourseWork070822-0.0.1-SNAPSHOT.jar myapp.jar

ENTRYPOINT ["java","-jar","/myapp.jar"]