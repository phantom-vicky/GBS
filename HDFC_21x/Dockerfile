
# Stage 1: Build application using Java 21
FROM maven:3.9-eclipse-temurin-21 AS builder

WORKDIR /app

COPY ./HDFC_21x/pom.xml .
COPY src ./HDFC_21x/src

RUN mvn -B clean package

# Stage 2: Tomcat with Java 21
FROM tomcat:10.1-jdk21-temurin

# Remove default applications
RUN rm -rf /usr/local/tomcat/webapps/*

# Deploy application as root context
COPY --from=builder /app/target/gbsultra_hdfc-1.0-SNAPSHOT.war \
    /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080

CMD ["catalina.sh", "run"]

