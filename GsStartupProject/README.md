# GsStartupProject
社内システムのバックエンド

## 使用技術
* プログラミング言語：java 1.8
* フレームワーク：SpringBoot 2.1.6
* ORM：JPA
* RDBMS：mysql 5.7
* 認証：JWT

## ビルド
```bash
./gradlew build --stacktrace
```

## 起動
```bash
./gradlew clean bootRun
```

## ログイン
```bash
curl -X POST -H "Content-Type: application/json" -d '{"loginId": "admin8080", "password": "admin8080"}' http://localhost:8080/api/v1/auth/signin
```
