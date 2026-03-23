# Etape 1
FROM maven:3.9-eclipse-temurin-24 AS compilation
WORKDIR /projet
COPY pom.xml pom.xml
RUN mvn dependency:resolve -B -q
COPY src src
RUN mvn package -B -DskipTests -q

# Etape 2
FROM eclipse-temurin:24-jre-alpine
WORKDIR /opt/boutique
COPY --from=compilation /projet/target/boutique.jar boutique.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "boutique.jar"]
