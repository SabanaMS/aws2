FROM openjdk:11

ADD ./target/TicketService-0.0.1-SNAPSHOT.jar /usr/src/TicketService-0.0.1-SNAPSHOT.jar

WORKDIR usr/src

ENTRYPOINT ["java","-jar", "TicketService-0.0.1-SNAPSHOT.jar"]

