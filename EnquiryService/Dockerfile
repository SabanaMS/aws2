FROM openjdk:11-jre-slim
WORKDIR usr/src
ENV MONGO_URI mongodb://mongo:27017/enquirydb
ENV RABBITMQ_HOST rabbitmq
ENV RABBITMQ_PORT 5672
ENV RABBITMQ_USERNAME guest
ENV RABBITMQ_PASSWORD guest
ADD ./target/EnquiryService-0.0.1-SNAPSHOT.jar /usr/src/EnquiryService-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","EnquiryService-0.0.1-SNAPSHOT.jar"]
