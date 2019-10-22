FROM node:10-alpine as builder
MAINTAINER emrecaglan
ADD /service-desk-app/package.json /service-desk-app/package-lock.json ./
RUN npm ci && mkdir /service-desk-app && mv ./node_modules ./service-desk-app
WORKDIR /service-desk-app
ADD /service-desk-app/. .
RUN npm run ng build -- --prod --output-path=dist

FROM nginx:1.14.1-alpine
ADD /service-desk-app/nginx/default.conf /etc/nginx/conf.d/
RUN rm -rf /usr/share/nginx/html/*
COPY --from=builder /service-desk-app/dist /usr/share/nginx/html
CMD ["nginx", "-g", "daemon off;"]