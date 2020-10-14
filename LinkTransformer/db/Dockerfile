FROM postgres:alpine
COPY init-user-db.sql /docker-entrypoint-initdb.d/
ADD init-user-db.sql /docker-entrypoint-initdb.d
RUN chmod a+r /docker-entrypoint-initdb.d/*
EXPOSE 5432