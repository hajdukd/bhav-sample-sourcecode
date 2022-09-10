FROM gradle:7.5.1-jdk11-jammy AS build
ADD . /src
WORKDIR /src
RUN gradle build

#------------------------------------------------------------#
FROM adoptopenjdk/openjdk11:alpine-jre AS base
ARG USER=app
ARG HOME=/opt/app
WORKDIR $HOME
RUN apk add --no-cache bash
COPY docker-entrypoint.sh .
ENV TINI_VERSION=v0.19.0
ADD https://github.com/krallin/tini/releases/download/${TINI_VERSION}/tini tini
RUN chmod +x docker-entrypoint.sh tini && \
    addgroup -S $USER && \
    adduser -S $USER -G $USER
ENTRYPOINT ["./tini", "--", "./docker-entrypoint.sh"]

#------------------------------------------------------------#
FROM base
ARG USER
ARG HOME
ARG JAR_SRC=/src/build/libs/*.jar
ARG JAR_DEST=app.jar
COPY --from=build $JAR_SRC $JAR_DEST
RUN chown -R $USER:$USER $HOME
USER $USER
EXPOSE 8080
#CMD ["--server.port=8080", "--spring.profiles.active=prod"]

