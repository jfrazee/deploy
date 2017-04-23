docker run -d --name=cassandra --net=host -p 7000:7000 -p 7001:7001 -p 7199:7199 -p 9042:9042 -p 9160:9160 cassandra:2.1.14

docker exec -it cassandra /bin/bash

cassandra-cli

create keyspace Usergrid_Applications
