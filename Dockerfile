FROM maven:latest AS build-stage
COPY . /build
WORKDIR /build
RUN mvn clean package

FROM java:1 AS runtime
WORKDIR /opt/hello-world
COPY --from=build-stage /build/target/SpringHedgehog*.jar app.jar
ENTRYPOINT ["/usr/bin/java", "-jar", "app.jar"]
