FROM openjdk:11-jre-slim
WORKDIR usr/src
ENV MONGO_URI mongodb://mongo:27017/programdb
ADD ./target/GymService-0.0.1-SNAPSHOT.jar /usr/src/GymService-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","GymService-0.0.1-SNAPSHOT.jar"]
