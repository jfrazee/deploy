## Start the Elasticsearch Containers

### boot2docker

```sh
$ docker run -d --name=elasticsearch1 --net=host \
    -p 9200:9200 \
    -p 9300:9300 \
    elasticsearch:1.7.5
$ docker run -d --name=elasticsearch2 --net=host \
    -p 9201:9200 \
    -p 9301:9300 \
    elasticsearch:1.7.5
```

### Docker for Mac

```sh
$ docker run -d --name=elasticsearch1 \
    -p 9200:9200 \
    -p 9300:9300 \
    elasticsearch:1.7.5
$ docker run -d --name=elasticsearch2 \
    -p 9201:9200 \
    -p 9301:9300 \
    elasticsearch:1.7.5
```

## Check that the Elasticsearch servers are running

```sh
$ curl "http://localhost:9200/_cluster/stats"
$ curl "http://localhost:9201/_cluster/stats"
```
