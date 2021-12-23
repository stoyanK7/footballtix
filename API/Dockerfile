FROM azul/zulu-openjdk-alpine:11 

RUN addgroup -S spring && adduser -S spring -G spring

USER spring:spring

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} /app.jar

ARG EXPECTED_ORIGIN

RUN sh create-env-file.sh EXPECTED_ORIGIN=$EXPECTED_ORIGIN

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app.jar"]