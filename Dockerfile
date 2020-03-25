FROM adoptopenjdk/openjdk11:latest

MAINTAINER Guna Sekhar Dora Kovvuru

ADD target/esbase-jar-with-dependencies.jar /esbase/esbase.jar

EXPOSE 9090

CMD ["java", "-jar", "/esbase/esbase.jar"]
