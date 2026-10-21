FROM node:24.18.0

ENV PNPM_HOME="/pnpm"
ENV PATH="$PNPM_HOME:$PATH"
ENV GITLAB_CI=true

RUN apt update
RUN apt upgrade -y

RUN npm install -g corepack@latest
RUN corepack enable
RUN pnpm dlx playwright@1.61.1 install --with-deps chromium firefox

WORKDIR /app

CMD ["tail", "-f", "/dev/null"]
