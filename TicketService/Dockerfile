FROM openjdk:11-jre-slim
WORKDIR usr/src
ENV MONGO_URI mongodb://mongo:27017/ticketdb
ENV RABBITMQ_HOST rabbitmq
ENV RABBITMQ_PORT 5672
ENV RABBITMQ_USERNAME guest
ENV RABBITMQ_PASSWORD guest
ADD ./target/TicketService-0.0.1-SNAPSHOT.jar /usr/src/TicketService-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","TicketService-0.0.1-SNAPSHOT.jar"]
