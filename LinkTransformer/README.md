# SelinKaya
A Web Service to convert URLs to deeplinks and deeplinks to URLs.

There are two endpoints for this transition.

1. web2deep
2. deep2web

For project setup, use postgres initilize script provided. 

* cd \LinkTransformer\docker-entrypoint-initdb.d\
* docker network create trendyol
* docker network ls
* docker run --name trendyol-db --network trendyol -p 5432:5432   -e POSTGRES_PASSWORD=Passw0rd -d postgres

done creating the db, next steps are not needed but incase if you want to check anything, you can use postbird or jump into db console by:
* docker exec -it trendyol-db bash

psql -U postgres -W

... insert password (Passw0rd)

to clean the docker parts after all is well(to shut down database and clean what is left):

* docker rm -f trendyol-db
* docker network rm trendyol

(for more info you can check:
https://medium.com/@selinkaya.ce/docker-cheatsheet-c3cc7cf83a5c
)