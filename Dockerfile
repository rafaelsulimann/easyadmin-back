FROM maven:3.8.4-openjdk-17-slim AS builder
WORKDIR /app
COPY . .
RUN mvn clean package

FROM openjdk:17-slim
WORKDIR /app
ENV APP_PROFILE=test
ENV CLIENT_ID=myclientid
ENV CLIENT_SECRET=myclientsecret
ENV JWT_SECRET=myjwtsecret
ENV JWT_DURATION=86400
ENV CORS_ORIGINS=https://easyadmin.com.br,http://localhost:3000,http://localhost:5173,
ENV POSTGRES_USERNAME=postgres
ENV POSTGRES_PASSWORD=1234567
ENV POSTGRES_HOST=host.docker.internal
ENV POSTGRES_PORT=5432
ENV POSTGRES_DATABASE=easyadmin
COPY --from=builder /app/target/easyadmin-0.0.1-SNAPSHOT.jar ./easyadmin.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/easyadmin.jar"]