# JavaWeb

# Usefull commands

```console
docker build --rm -t blackstar:latest .

docker run -d -p 4200:8080 blackstar:latest

docker ps

docker exec -it name /bin/sh

docker stop
```
docker run --network=blackstar_bs-net -p 4200:4200 bs-app
 docker build . -t bs-app --network=blackstar_bs-net