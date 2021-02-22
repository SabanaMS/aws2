FROM openjdk:11

ADD ./target/ApiGatewayService-0.0.1-SNAPSHOT.jar /usr/src/ApiGatewayService-0.0.1-SNAPSHOT.jar

WORKDIR usr/src

ENTRYPOINT ["java","-jar","ApiGatewayService-0.0.1-SNAPSHOT.jar"]
