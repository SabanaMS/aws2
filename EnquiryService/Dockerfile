FROM openjdk:11
ADD ./target/EnquiryService-0.0.1-SNAPSHOT.jar /usr/src/EnquiryService-0.0.1-SNAPSHOT.jar
WORKDIR usr/src
ENV MONGO_URI mongodb://mongo:27017/goldysgym
ENV RABBITMQ_HOST rabbitmq
ENV RABBITMQ_PORT 5672
ENV RABBITMQ_USERNAME guest
ENV RABBITMQ_PASSWORD guest
ENTRYPOINT ["java","-jar","EnquiryService-0.0.1-SNAPSHOT.jar"]

