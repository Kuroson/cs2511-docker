ARG NODE_VERSION=24.12.0
ARG ALPINE_VERSION=3.22

FROM node:${NODE_VERSION}-alpine AS node

FROM alpine:${ALPINE_VERSION} AS base

COPY --from=node /usr/lib /usr/lib
COPY --from=node /usr/local/lib /usr/local/lib
COPY --from=node /usr/local/include /usr/local/include
COPY --from=node /usr/local/bin /usr/local/bin
ARG GRADLE_VERSION=9.2.0

# Set some environment variables
ENV GRADLE_HOME=/opt/gradle
ENV GRADLE_USER_HOME=/gradle
ENV PATH=$GRADLE_HOME/bin:$PATH
ENV GRADLE_OPTS="-Dorg.gradle.console=plain"

# Install OpenJDK 21 and other required dependencies
RUN apk update
RUN apk upgrade
RUN apk --no-cache add openjdk21 wget unzip
RUN apk --no-cache add git bash curl

# Download and unpack Gradle
RUN wget https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip -P /tmp
RUN unzip /tmp/gradle-${GRADLE_VERSION}-bin.zip -d /opt
RUN ln -s /opt/gradle-${GRADLE_VERSION} /opt/gradle
RUN rm /tmp/gradle-${GRADLE_VERSION}-bin.zip

# Set the working directory
WORKDIR /gradle

CMD ["tail", "-f", "/dev/null"]
