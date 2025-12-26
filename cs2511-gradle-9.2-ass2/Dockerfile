# Start with a base Alpine Linux image
FROM alpine:3.22

# Set the Gradle version
ARG GRADLE_VERSION=9.2.0

# Set some environment variables
ENV GRADLE_HOME=/opt/gradle
ENV GRADLE_USER_HOME=/gradle
ENV PATH=$GRADLE_HOME/bin:$PATH

# Install OpenJDK 21 and other required dependencies
RUN apk --no-cache add openjdk21 wget unzip

# Download and unpack Gradle
RUN wget https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip -P /tmp && \
    unzip /tmp/gradle-${GRADLE_VERSION}-bin.zip -d /opt && \
    ln -s /opt/gradle-${GRADLE_VERSION} /opt/gradle && \
    rm /tmp/gradle-${GRADLE_VERSION}-bin.zip

# Download the assignment file and place it in /data
RUN wget https://cgi.cse.unsw.edu.au/~cs2511/public/dryrun-files/dryrun_ass2/assignment-ii-tests.tar -P /data

# Install other tools
RUN apk --no-cache add git bash curl

# Set the working directory
WORKDIR /gradle

CMD ["tail", "-f", "/dev/null"]
