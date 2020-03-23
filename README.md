# BlackStar

## Start project

from root folder
```console
docker-compose up -d

docker build . -t bs-app --network=blackstar_bs-net

docker run --network=blackstar_bs-net -p 4200:4200 bs-app
```
