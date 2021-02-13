FROM openjdk:11

ADD ./target/EurekaService-0.0.1-SNAPSHOT.jar /usr/src/EurekaService-0.0.1-SNAPSHOT.jar

WORKDIR usr/src

ENTRYPOINT ["java","-jar", "EurekaService-0.0.1-SNAPSHOT.jar"]
