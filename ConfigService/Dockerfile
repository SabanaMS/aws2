FROM openjdk:11-jre-slim
WORKDIR usr/src
ADD ./target/ConfigService-0.0.1-SNAPSHOT.jar /usr/src/ConfigService-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","ConfigService-0.0.1-SNAPSHOT.jar"]
