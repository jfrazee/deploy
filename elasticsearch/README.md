# Local Setup

docker run -d --name=elasticsearch1 --net=host -p 9200:9200 -p 9300:9300 elasticsearch:1.7.5

docker run -d --name=elasticsearch2 --net=host -p 9200:9201 -p 9300:9301 elasticsearch:1.7.5

curl "docker:9200/_cluster/stats"

# Distributed Setup
