# Gradle + Node image with a warmed Gradle dependency cache.
#
# This image is self-contained: the build context is this folder, and the seed
# Gradle project lives in ./seed. The extra steps at the bottom resolve the
# whole dependency graph so CI can build with --offline and never hit Maven
# Central.

ARG NODE_VERSION=24.18.0
ARG ALPINE_VERSION=3.24.1

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
RUN apk --no-cache add openjdk21 wget unzip git bash curl openssh-client

# Download and unpack Gradle
RUN wget https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip -P /tmp
RUN unzip /tmp/gradle-${GRADLE_VERSION}-bin.zip -d /opt
RUN ln -s /opt/gradle-${GRADLE_VERSION} /opt/gradle
RUN rm /tmp/gradle-${GRADLE_VERSION}-bin.zip

# --- Warm the Gradle cache -------------------------------------------------
# Copy the seed project (declares the union of every dependency, plugin and
# tool version used across the COMP2511 26T2 repos) and resolve the entire
# graph. This bakes the app deps + transitives, the plugin classpath
# (jacocolog -> commons-lang3), the Checkstyle/JaCoCo tool jars and the Gradle
# wrapper distribution into GRADLE_USER_HOME (/gradle).
COPY seed /seed
RUN cd /seed && \
    ./gradlew --no-daemon --init-script seed-init.gradle resolveAllDependencies build && \
    rm -rf /seed && \
    chmod -R a+rwX "$GRADLE_USER_HOME"

# Set the working directory
WORKDIR /gradle

CMD ["tail", "-f", "/dev/null"]
