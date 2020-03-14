### build環境 ###
FROM openjdk:8-jdk-alpine
#RUN addgroup -S apladmin && adduser -S apladmin -G apladmin
#USER apladmin:apladmin

#WORKDIR /app
#COPY . /app
#RUN /app/gradlew build --stacktrace

### production環境 ###


# ソースのコピー
COPY ./build/libs/intranet-springboot-app-0.1.0.jar app.jar
# ./gradlew build && java -jar build/libs/intranet-springboot-app-0.1.0.jar


# ローカル環境では、docker-composeのentrypointに上書きされる
ENTRYPOINT ["java","-Dspring.profiles.active=kubernetes","-jar","app.jar"]

# CMD ./gradlew bootRun
