# RedSkyMicroService
RedSky MicroService

Download docker in MAC below link have the steps 
https://runnable.com/docker/install-docker-on-macos

Execute below commands to pull latest cassandra image 
sudo docker pull cassandra
The version of the downloaded Cassandra image can be checked with following command:
(dockerhost)$ sudo docker run -it --rm --name cassandra cassandra -v


sudo docker run -it --rm --name docker-cassandra -p7000:7000 -p7001:7001 -p9042:9042 -p9160:9160 cassandra:latest

sudo docker exec -it docker-cassandra bash

cqlsh 127.0.0.1 9042 -u cassandra -p cassandra


CREATE KEYSPACE redsky1
WITH replication = {'class':'SimpleStrategy', 'replication_factor' : 3};

CREATE TABLE redsky1."redsky" ( productid varint, name varchar, PRIMARY KEY (productid) );

http://localhost:8082/swagger-ui.html#!/red-sky-controller
https://dzone.com/articles/spring-boot-restful-api-documentation-with-swagger
http://localhost:8082/v2/api-docs
