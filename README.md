# spring-boot-book-app
Books managing application using Spring Boot

## Tech stack
- Java: 25
- Spring Boot: 3.5.7
- Database: PostgreSql:18
- Database Migration: Flyway
- Containerization: Docker

##

[GIT](https://github.com/muneer2ishtech/springboot-book-app)


## Design
- [ishtech-jpa-base](https://github.com/ishtech/ishtech-base-jpa) - Foundational JPA and other base classes
- [ishtech-springboot-jwtauth](https://github.com/ishtech/ishtech-springboot-jwtauth) - For Authentiation & Authorization

## APIs

- For details you can see swagger documentation
    - [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
    - [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)
- Note: Check and update URI and PORT on which application is running

- For Authentiation & Authorization APIs:
    - See [ishtech-springboot-jwtauth](https://github.com/ishtech/ishtech-springboot-jwtauth)


## DB

### Local
- You need local instance or docker of local PostgreSQL

- I have customized docker for various databases
    - For PostgreSQL
        - See [https://github.com/IshTech/docker-db/tree/main/postgres](https://github.com/IshTech/docker-db/tree/main/postgres)

- Login to DB as `root` / `superuser` and run [init_db.sql](src/test/resources/db/init_db.sql) to setup DB Schema, DB User and Grant privileges

#### DB Access

```
psql -U ishtech_dev_user -W -d ishtech_dev_db
```

- Enter password on prompt `ishtech_dev_pass`

### Flyway migration files
- Path `src/main/resources/db/migration/`
- To create migration files with date and time in the file name
    - E.g. `V20251021_103045__create_table_book.sql`

```
touch src/main/resources/db/migration/V$(date +"%Y%m%d_%H%M%S")__create_table_TODO_PUT_TABLE_NAME_WITHOUT_PREFIX.sql

```


## Build and Run

- Ensure the port, db properties etc are correct in application-xxx.properties

### Local Gradle Build

- Build without tests

```
./gradlew clean build -x test
```

- Build with Junit tests

```
./gradlew clean build
```

- Run

```
./gradlew bootRun --args='--spring.profiles.active=dev'
```
