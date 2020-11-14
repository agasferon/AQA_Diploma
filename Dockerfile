FROM openjdk:8-slim
WORKDIR /opt/app
COPY . .
CMD ["java", "-jar", "./artifacts/aqa-shop.jar"]
EXPOSE 8080