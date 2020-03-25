# BlackStar

## Start project

from root folder

```console
docker-compose -f db.docker-compose.yml up -d
docker-compose -f app.docker-compose.yml up -d
```

## Other usefull command

```console
docker build . -t bs-app --network=blackstar_bs-net
docker run --network=blackstar_bs-net -p 4200:4200 --env-file=.env bs-app
```
