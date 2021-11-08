# Book Store

This project simulates a Book Store API.

## Installation

```bash
git clone https://github.com/ismayilkarimli/bookstore
```

## Running
There are two ways to run the code:  
1. Using Docker:
```bash
docker compose up
```
or  
```bash
docker-compose up
```
2. Maven:  
* For Maven based approach there are certain prerequisites:   
   1. Maven 
   2. JDK 17+
   3. Postgres
   4. Make the necessary changes in the `application.properties` (needed changes are commented in the file)
```bash
mvn clean && mvn spring-boot:start
```
###### Note: Ports 5432 and 8080 should be free to run the application.  
## Usage:
Usage information can be obtained from `localhost:8080/swagger-ui/` and `http://localhost:8080/v3/api-docs/`
