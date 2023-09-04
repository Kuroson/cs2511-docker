FROM python:3.9-alpine

RUN apk add bash git
RUN apk add -U tzdata libxslt libxml2 libjpeg zlib libffi freetype ttf-dejavu libpq
RUN cp /usr/share/zoneinfo/Australia/Sydney /etc/localtime
RUN apk del tzdata
RUN rm -rf /var/cache/apk/*


RUN pip3 install poetry
COPY pyproject.toml poetry.lock ./
RUN poetry export --format=requirements.txt > requirements.txt
RUN pip3 install -r requirements.txt
RUN rm requirements.txt pyproject.toml poetry.lock

CMD ["tail", "-f", "/dev/null"]
