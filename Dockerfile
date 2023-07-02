FROM openjdk:17-alpine3.14

ADD target/api-servico-0.0.1-SNAPSHOT.war app.war

ENTRYPOINT [ "java", "-jar", "app.war" ]

EXPOSE 8080