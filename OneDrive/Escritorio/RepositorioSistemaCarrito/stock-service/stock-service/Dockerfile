FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/stock-service-0.0.1.jar
COPY ${JAR_FILE} app_stock.jar
ENTRYPOINT ["java", "-jar", "app_stock.jar"]