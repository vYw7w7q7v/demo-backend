
FROM maven:3.8.4-openjdk-17 as builder
WORKDIR /app
COPY . /app/.
COPY /src/main/resources/application-docker.yml /app/application.yml
#RUN mvn clean install spring-boot:repackage -Dmaven.test.skip=true
RUN mvn -f /app/pom.xml clean install spring-boot:repackage -Dmaven.test.skip=true

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=builder /app/target/*.jar /app/*.jar
COPY --from=builder /app/application.yml /app/application.yml
EXPOSE 8181
ENTRYPOINT ["java", "-jar", "/app/*.jar", "application.yml"]