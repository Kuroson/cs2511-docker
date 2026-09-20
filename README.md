# cs2511 docker images

This repository contains the Dockerfiles for the cs2511 course.

## Layout

Each image lives in its own top-level folder, which is also its Docker build
context. Images that are no longer built live under [`archive/`](./archive),
with their workflows parked in `.github/archive-workflows/`.

Currently built:

| Folder | Image | Notes |
| --- | --- | --- |
| [`cs2511-gradle-9.2-cache`](./cs2511-gradle-9.2-cache) | `cs2511docker/cs2511-gradle-9.2-cache` | Gradle 9.2 + warmed cache for the `lab0*` repos |
| [`cs2511-gradle-9.2-ass1-cache`](./cs2511-gradle-9.2-ass1-cache) | `cs2511docker/cs2511-gradle-9.2-ass1-cache` | Gradle 9.2 + assignment-i tests + warmed cache |
| [`cs2511-gradle-9.2-ass2-cache`](./cs2511-gradle-9.2-ass2-cache) | `cs2511docker/cs2511-gradle-9.2-ass2-cache` | Gradle 9.2 + assignment-ii tests + warmed cache |
| [`cs2511-automarking`](./cs2511-automarking) | `cs2511docker/cs2511-automarking` | Gradle 9.2 + Node 24.18 + warmed cache |
| [`cs2511-playwright-1.61.1-24.18.0`](./cs2511-playwright-1.61.1-24.18.0) | `cs2511docker/cs2511-playwright-1.61.1-24.18.0` | Playwright 1.61.1 on Node 24.18 |

### Warmed Gradle caches

The `*-cache` images (and `cs2511-automarking`) each ship a throwaway Gradle
project under `seed/` that declares the union of every dependency, plugin and
tool version used by the repos the image marks. The Dockerfile builds it with
`seed-init.gradle`, which resolves every resolvable configuration, then deletes
the project — leaving the whole dependency graph, the Checkstyle/JaCoCo tool
jars and the Gradle wrapper distribution baked into `GRADLE_USER_HOME`
(`/gradle`). CI can then build with `--offline` and never hit Maven Central.

When a lab or assignment repo gains a dependency, add it to the matching
`seed/app/build.gradle`, reference it from `seed/app/src/**` so the build
actually pulls it down, and push — the workflow rebuilds and republishes.

## Usage

To build an image, run (substituting the folder name):

```bash
docker build -t cs2511docker/cs2511-gradle-9.2-cache:latest ./cs2511-gradle-9.2-cache
```

To publish:

```bash
docker push cs2511docker/cs2511-gradle-9.2-cache:latest
```

## CI

Could not get the CI to run docker-in-docker. Used GitHub actions to build & publish the images to Docker Hub.

Each folder has a matching workflow in `.github/workflows/`, triggered on pushes
that touch the folder or the workflow itself, plus `workflow_dispatch`. Images
are tagged `:latest` and `:<short-sha>`.
