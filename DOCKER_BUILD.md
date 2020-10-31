# Steps for Building Docker Image & Run It

1. `gradle clean`
2. `gradle build`
3. `docker build --tag kotlin-restful:0.0.1 .` 
    - Change 0.0.1 to version.
4. Check docker image creation:
    - `docker image ls`
5. Run docker compose:
    - `docker-compose -f docker-compose.yml up -d`
6. Check docker container:
    - `docker container ls`
7. Check docker logs:
    - `docker container logs kotlin-restful`
    
## Change `docker-compose.yml` to use Postgre:

```yaml
version: '3.5'

services:
  kotlin-restful-api:
    container_name: kotlin-restful-api
    image: kotlin-restful-api:0.0.1
    ports:
      - 8080:8080
    environment:
      DATABASE_USERNAME: kotlin
      DATABASE_PASSWORD: kotlin
      DATABASE_URL: jdbc:postgresql://kotlin-restful-api-postgres:5432/restful-api
  kotlin-restful-api-postgres:
    container_name: "kotlin-restful-api-postgres"
    image: postgres:12-alpine
    ports:
      - 5432:5432
    environment:
      POSTGRES_PASSWORD: kotlin
      POSTGRES_USER: kotlin
      POSTGRES_DB: restful-api
```