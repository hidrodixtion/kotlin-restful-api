FROM adoptopenjdk/openjdk11:jre-11.0.6_10-alpine

COPY build/libs/kotlin-restful-0.0.1-SNAPSHOT.jar /app/application.jar

CMD ["java", "-jar", "/app/application.jar"]