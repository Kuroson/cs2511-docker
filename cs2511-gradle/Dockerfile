# Start with a base Alpine Linux image
FROM alpine:3.18

# Set the Gradle version
ARG GRADLE_VERSION=7.2

# Set some environment variables
ENV GRADLE_HOME /opt/gradle
ENV GRADLE_USER_HOME /gradle
ENV PATH $GRADLE_HOME/bin:$PATH

# Install OpenJDK 11 and other required dependencies
RUN apk --no-cache add openjdk11 wget unzip

# Download and unpack Gradle
RUN wget https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip -P /tmp && \
    unzip /tmp/gradle-${GRADLE_VERSION}-bin.zip -d /opt && \
    ln -s /opt/gradle-${GRADLE_VERSION} /opt/gradle && \
    rm /tmp/gradle-${GRADLE_VERSION}-bin.zip

# Set the working directory
WORKDIR /gradle

CMD ["tail", "-f", "/dev/null"]
