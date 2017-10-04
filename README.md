spring-five

### Reactive, Reactive Mongo

#####Java Backend
mvn spring-boot:run or start with Intellj Idea's Spring Boot

#####MongoDB
docker pull mongo
docker run --name springdemo -p 27017:27017 mongo
or docker start springdemo

create new database in mongo springdemo
create new collection Human
insert some data

#####test Api
Postman:
http://localhost:8080/api/humans/{id}
http://localhost:8080/api/humans/59d0a4bea9296ec2aca135d5
