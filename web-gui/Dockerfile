### build環境 ###
FROM node:12.6 as build-stage

# build
WORKDIR /app
COPY ./intranet-front-web ./
WORKDIR /app/vue
RUN npm install
ARG API_PATH
ENV VUE_APP_API_URL=${API_PATH}
RUN npm run build

### production環境 ###
FROM nginx:stable-alpine AS production-stage

# 環境変数
ENV LANG C.UTF-8
ENV TZ Asia/Tokyo

# Nginx.conf の配置
COPY nginx/nginx.conf /etc/nginx/nginx.conf

# ビルドしたファイルを配置
RUN mkdir -p /var/log/nginx/log\
    && mkdir /home/www\
    && mkdir /home/www/html
COPY --from=build-stage /app/dist /home/www/html
#EXPOSE 80 443

# Nginx起動
CMD ["nginx", "-g", "daemon off;","-c","/etc/nginx/nginx.conf"]
