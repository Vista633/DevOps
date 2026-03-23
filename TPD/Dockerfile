# Etape 1 : build du war avec maven
FROM maven:3.9-eclipse-temurin-17 AS compilation
WORKDIR /projet
COPY pom.xml pom.xml
RUN mvn dependency:resolve -B -q
COPY src src
RUN mvn package -DskipTests -B -q

# Etape 2 : deploiement sur tomcat
FROM tomcat:10.1-jre17-temurin-jammy AS runtime
RUN rm -rf /usr/local/tomcat/webapps/*
COPY --from=compilation /projet/target/boutique.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080
ENTRYPOINT ["catalina.sh", "run"]
