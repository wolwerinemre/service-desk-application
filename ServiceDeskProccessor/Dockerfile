FROM openjdk:8-jdk-alpine as build
MAINTAINER emrecaglan
RUN mkdir -p /service-desk-proccessor/
ADD ./build/libs/ServiceDeskProccessor.jar ./service-desk-proccessor/ServiceDeskProccessor.jar
ENTRYPOINT ["java", "-jar", "/service-desk-proccessor/ServiceDeskProccessor.jar"]
HEALTHCHECK --start-period=3m CMD curl --silent --write-out "HTTPSTATUS:%{http_code}" \
http://localhost:8081/actuator/health | sed -e 's/HTTPSTATUS\:.*//g' | grep '{"status":"UP"}' || exit 1