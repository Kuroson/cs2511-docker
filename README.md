# cs2511 docker images

This repository contains the Dockerfiles for the cs2511 course.

## Usage

To build the [cs2511-gradle docker image](./cs2511-gradle/Dockerfile), run:

```bash
docker build -t cs2511docker/cs2511-gradle:latest ./cs2511-gradle
```

To publish:

```bash
docker push cs2511docker/cs2511-gradle:latest
```

## CI

Could not get the CI to run docker-in-docker. Used GitHub actions to build & publish the images to Docker Hub.
