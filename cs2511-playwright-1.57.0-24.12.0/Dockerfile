FROM node:24.12.0

ENV PNPM_HOME="/pnpm"
ENV PATH="$PNPM_HOME:$PATH"
ENV GITLAB_CI=true

RUN apt update
RUN apt upgrade -y

RUN npm install -g corepack@latest
RUN corepack enable
RUN pnpm dlx playwright@1.57.0 install --with-deps chromium

# RUN mkdir -p /root/.cache/ms-playwright
# RUN cp -rv /ms-playwright /root/.cache/
# RUN rm -rfv /ms-playwright || true

WORKDIR /app

CMD ["tail", "-f", "/dev/null"]