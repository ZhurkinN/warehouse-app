FROM openjdk:17
ARG jarFile=target/warehouse-app-0.0.1-SNAPSHOT.jar
WORKDIR /opt/app
COPY ${jarFile} warehouse.jar
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "warehouse.jar"]
