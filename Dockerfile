FROM maven:3.9.2-eclipse-temurin-17 AS Builder
WORKDIR /

ADD wallet-core/pom.xml core/pom.xml
ADD wallet-core/src core/src/

ADD wallet-web/pom.xml web/pom.xml
ADD wallet-web/src web/src/

COPY wallet-web/data/apikey.txt web/data/apikey.txt

RUN mvn clean install -DskipTests -f core/pom.xml
RUN mvn clean install -DskipTests -f web/pom.xml

FROM openjdk:17

COPY wallet-web/data/apikey.txt data/apikey.txt
COPY --from=Builder /web/target/wallet-web-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]