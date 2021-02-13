FROM openjdk:11

ADD ./target/ConfigService-0.0.1-SNAPSHOT.jar /usr/src/ConfigService-0.0.1-SNAPSHOT.jar

WORKDIR usr/src

ENTRYPOINT ["java","-jar", "ConfigService-0.0.1-SNAPSHOT.jar"]

