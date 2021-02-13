FROM openjdk:11

ADD ./target/EnquiryService-0.0.1-SNAPSHOT.jar /usr/src/EnquiryService-0.0.1-SNAPSHOT.jar

WORKDIR usr/src

ENV MONGO_URI mongodb://localhost:27017/goldysgym

ENTRYPOINT ["java","-jar", "EnquiryService-0.0.1-SNAPSHOT.jar]

