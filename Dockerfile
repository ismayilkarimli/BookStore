FROM openjdk:17-oracle
COPY target/bookstore-0.0.1-SNAPSHOT.jar bookstore-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/bookstore-0.0.1-SNAPSHOT.jar"]