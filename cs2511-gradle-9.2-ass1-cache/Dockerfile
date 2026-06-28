# Identical to cs2511-gradle-9.2-ass1, plus a warmed Gradle dependency cache.
#
# This image is self-contained: the build context is this folder, and the seed
# Gradle project lives in ./seed. The extra steps at the bottom resolve the
# whole dependency graph so CI can build with --offline and never hit Maven
# Central.

# Start with a base Alpine Linux image
FROM alpine:3.23


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
RUN wget https://cgi.cse.unsw.edu.au/~cs2511/public/dryrun-files/dryrun_ass1/assignment-i-tests.tar -P /data

# Install other tools
RUN apk --no-cache add git bash curl

# --- Warm the Gradle cache -------------------------------------------------
# Copy the seed project (declares the union of every dependency, plugin and
# tool version used across the assignment repos) and resolve the entire graph.
# This bakes the app deps + transitives, the plugin classpath
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
