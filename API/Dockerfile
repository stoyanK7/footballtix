FROM azul/zulu-openjdk-alpine:11 

RUN addgroup -S spring && adduser -S spring -G spring

RUN mkdir -p /src/main/resources/templates

RUN mkdir -p /fbtxtmp/ticket

RUN mkdir -p /fbtxtmp/qr

RUN mkdir -p /fbtxtmp/receipt

RUN chmod o+x /fbtxtmp

RUN chmod o+x /fbtxtmp/*

RUN chown spring:spring /fbtxtmp/*

USER spring:spring

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} /app.jar

COPY src/main/resources/templates/* /src/main/resources/templates/

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app.jar"]