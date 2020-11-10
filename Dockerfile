FROM node:8.16.2-alpine
WORKDIR /opt/app
COPY . .
RUN npm install
CMD ["npm", "start"]
EXPOSE 9999
FROM openjdk:8-slim
WORKDIR /opt/app
COPY . .
CMD ["java", "-jar", "./artifacts/aqa-shop.jar"]
EXPOSE 8080