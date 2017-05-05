## Start the Cassandra Container

### boot2docker

```sh
$ docker run -d --name=cassandra --net=host \
    -p 7000:7000 \
    -p 7001:7001 \
    -p 7199:7199 \
    -p 9042:9042 \
    -p 9160:9160 \
    cassandra:2.1.14
```

### Docker for Mac

```sh
$ docker run -d --name=cassandra \
    -p 7000:7000 \
    -p 7001:7001 \
    -p 7199:7199 \
    -p 9042:9042 \
    -p 9160:9160 \
    cassandra:2.1.14
```

## Create the Usergrid Keyspace

```sh
$ docker exec -it cassandra /bin/bash
$ cqlsh -e "CREATE KEYSPACE Usergrid_Applications WITH replication = {'class': 'SimpleStrategy','replication_factor': '3'};"
```
