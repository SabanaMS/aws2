FROM openjdk:11-jre-slim
WORKDIR usr/src
ENV MYSQL_URL jdbc:mysql://mysql:3306/userdb
ENV MYSQL_USERNAME root
ENV MYSQL_PASSWORD password
ADD ./target/UserService-0.0.1-SNAPSHOT.jar /usr/src/UserService-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","UserService-0.0.1-SNAPSHOT.jar"]
