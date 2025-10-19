# ---- Build stage ----
FROM maven:3.8.8-eclipse-temurin-11 AS builder
WORKDIR /workspace

# Copy pom files first to leverage Docker layer caching
COPY pom.xml ./
COPY application/pom.xml application/pom.xml
COPY adapter/pom.xml adapter/pom.xml

# Pre-fetch dependencies
RUN mvn -q -e -DskipTests dependency:go-offline

# Copy sources
COPY application/ application/
COPY adapter/ adapter/

# Build only the adapter module (and its deps)
RUN mvn -q -DskipTests -pl adapter -am package

# ---- Runtime stage ----
FROM eclipse-temurin:11-jre
WORKDIR /app

# Copy the built jar from the builder
COPY --from=builder /workspace/adapter/target/*.jar /app/app.jar

EXPOSE 8080
ENV JAVA_OPTS=""

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /app/app.jar"]
