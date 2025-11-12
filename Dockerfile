# ====== Stage 1: Build ======
FROM eclipse-temurin:25-jdk AS build

COPY . .

RUN chmod +x ./gradlew
RUN ./gradlew clean build -x test

# ====== Stage 2: Runtime ======
FROM eclipse-temurin:25-jre

ARG SERVER_PORT=8080
#ENV SERVER_PORT=${SERVER_PORT}
EXPOSE ${SERVER_PORT:-8080}

COPY --from=build build/libs/ishtech-springboot-book-app-*.jar ishtech-springboot-book-app.jar

ENTRYPOINT ["java", "-jar", "ishtech-springboot-book-app.jar"]
