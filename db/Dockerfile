FROM postgres:latest
ENV POSTGRES_PASSWORD=postgres
ENV POSTGRES_USER=postgres
ENV POSTGRES_DB=platform
COPY schema.sql /docker-entrypoint-initdb.d/schema.sql

