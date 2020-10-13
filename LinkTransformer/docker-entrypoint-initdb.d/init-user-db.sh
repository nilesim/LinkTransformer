#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL

    CREATE DATABASE linker;
    CREATE TABLE web_deep_conversion(
       web VARCHAR (255) UNIQUE,
       deep VARCHAR (255) UNIQUE
    );
    CREATE TABLE path_lookup(
       key_1 VARCHAR (255),
       key_2 VARCHAR (255),
       value VARCHAR (255)
    );



EOSQL