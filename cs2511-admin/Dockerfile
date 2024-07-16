FROM debian:bookworm-slim

RUN rm /bin/sh && ln -s /bin/bash /bin/sh

RUN cp /usr/share/zoneinfo/Australia/Sydney /etc/localtime
RUN apt update && apt install -y
RUN apt install wget curl git unzip -y

# Python
RUN apt install python3.11 python3-pip -y
RUN curl -sSL https://install.python-poetry.org | POETRY_HOME=/etc/poetry python3 -
ENV PATH="$PATH:/etc/poetry/bin"

# NodeJS
ARG NODE_VERSION=18.19.0
RUN curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.39.1/install.sh | bash
RUN source ~/.nvm/nvm.sh && nvm install ${NODE_VERSION} && nvm use ${NODE_VERSION} && nvm alias default ${NODE_VERSION}

# Install package managers
RUN source ~/.nvm/nvm.sh && npm install -g yarn
RUN source ~/.nvm/nvm.sh && npm install -g pnpm

# Java
RUN apt install openjdk-17-jdk -y
ARG GRADLE_VERSION=8.8
ENV GRADLE_HOME /opt/gradle
ENV GRADLE_USER_HOME /gradle
ENV PATH $GRADLE_HOME/bin:$PATH

# Download and unpack Gradle
RUN wget https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip -P /tmp && \
    unzip /tmp/gradle-${GRADLE_VERSION}-bin.zip -d /opt && \
    ln -s /opt/gradle-${GRADLE_VERSION} /opt/gradle && \
    rm /tmp/gradle-${GRADLE_VERSION}-bin.zip

CMD ["tail", "-f", "/dev/null"]
