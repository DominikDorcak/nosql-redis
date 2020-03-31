#!/bin/bash
docker-compose up --build -d
docker exec -it redis-1 redis-cli -p 7000 --cluster create 10.0.0.2:7000 10.0.0.3:7001 10.0.0.4:7002 --cluster-replicas 0

exit