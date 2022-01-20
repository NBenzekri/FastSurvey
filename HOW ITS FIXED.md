### 1- mondodb authentication failled 18
run docker compose of mongo

> docker-compose up -d

> docker ps

> docker exec -it <mongo_container_id> bash

> mongo -u mg_user

> use fastsurvey

> db.createUser({user: "mg_user", pwd: "mg_pass", roles : [{role: "readWrite", db: "fastsurvey"}]});

the run spring boot app

