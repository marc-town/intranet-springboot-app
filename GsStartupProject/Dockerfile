FROM openjdk:8-jdk-alpine

# build
WORKDIR /app
COPY . /app
RUN /app/gradlew build --info

RUN cp -p ./build/libs/intranet-springboot-app-0.1.0.jar app.jar

# ローカル環境では、docker-composeのentrypointに上書きされる
ENTRYPOINT ["java","-Dspring.profiles.active=kubernetes","-jar","app.jar"]
