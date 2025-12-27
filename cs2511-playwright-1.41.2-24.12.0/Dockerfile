FROM mcr.microsoft.com/playwright:v1.41.2

ENV PNPM_HOME="/pnpm"
ENV PATH="$PNPM_HOME:$PATH"
ENV GITLAB_CI=true

RUN apt update
RUN apt upgrade -y

# Install Node.js 24.12.0 using NodeSource repository
RUN curl -fsSL https://deb.nodesource.com/setup_24.x | bash -
RUN apt-get update
RUN apt-get install -y nodejs
RUN rm -rf /var/lib/apt/lists/*
RUN node --version
RUN npm --version

RUN npm install -g corepack@latest
RUN corepack enable

RUN mkdir -p /root/.cache/ms-playwright
RUN cp -rv /ms-playwright /root/.cache/

WORKDIR /app

CMD ["tail", "-f", "/dev/null"]