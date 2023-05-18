FROM openjdk:17
WORKDIR /
COPY target/wallet-web-0.0.1-SNAPSHOT.jar wallet-web-0.0.1-SNAPSHOT.jar
COPY data/apikey.txt data/apikey.txt
EXPOSE 8081
CMD java -jar wallet-web-0.0.1-SNAPSHOT.jar