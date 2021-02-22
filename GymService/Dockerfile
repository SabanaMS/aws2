FROM openjdk:11

ADD ./target/GymService-0.0.1-SNAPSHOT.jar /usr/src/GymService-0.0.1-SNAPSHOT.jar

WORKDIR usr/src

ENV MONGO_URI mongodb://mongo:27017/goldysgympgm

ENTRYPOINT ["java","-jar","GymService-0.0.1-SNAPSHOT.jar"]