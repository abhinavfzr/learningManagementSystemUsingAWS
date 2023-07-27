FROM openjdk:11

ADD ./target/course-service-0.0.1-SNAPSHOT.jar /usr/src/course-service-0.0.1-SNAPSHOT.jar

WORKDIR usr/src

EXPOSE 8081

ENTRYPOINT ["java","-jar", "course-service-0.0.1-SNAPSHOT.jar"]