# spring-boot-book-app
Books managing application using Spring Boot

## Requirements
- JDK:25
- PostgreSql:18


TODO: more information

## Database

- `BIGINT` vs `INT` for `id` columns
     - `BIGINT` is `-2^63` to `2^63-1` (64 bits or 8 bytes)
     - `INT` is `-2^31` to `2^31-1` (32 bits or 4 bytes)
         - giving more than 2 billion positive values, enough for any app

### Dev DB setup
- Run following for first time before launching spring-boot-boot-app after PostgreSql is started

```
\connect ishtech_dev_db;

-- Note: CREATE USER is alias for CREATE ROLE WITH LOGIN
CREATE USER bookapp_dev_user        PASSWORD 'bookapp_dev_pass'        NOSUPERUSER;
CREATE USER bookapp_dev_flyway_user PASSWORD 'bookapp_dev_flyway_pass' NOSUPERUSER;

GRANT CONNECT ON DATABASE ishtech_dev_db TO bookapp_dev_user;
GRANT CONNECT ON DATABASE ishtech_dev_db TO bookapp_dev_flyway_user;

CREATE SCHEMA bookapp_dev_schema;
CREATE SCHEMA bookapp_dev_aud_schema;

GRANT USAGE ON SCHEMA bookapp_dev_schema     TO bookapp_dev_user;
GRANT USAGE ON SCHEMA bookapp_dev_aud_schema TO bookapp_dev_user;

GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA bookapp_dev_schema     TO bookapp_dev_user;
GRANT SELECT, INSERT                 ON ALL TABLES IN SCHEMA bookapp_dev_aud_schema TO bookapp_dev_user;

-- Note: Future tables to inherit same privileges
ALTER DEFAULT PRIVILEGES IN SCHEMA bookapp_dev_schema     GRANT SELECT, INSERT, UPDATE, DELETE ON TABLES TO bookapp_dev_user;
ALTER DEFAULT PRIVILEGES IN SCHEMA bookapp_dev_aud_schema GRANT SELECT, INSERT                 ON TABLES TO bookapp_dev_user;

GRANT USAGE ON SCHEMA bookapp_dev_schema     TO bookapp_dev_flyway_user;
GRANT USAGE ON SCHEMA bookapp_dev_aud_schema TO bookapp_dev_flyway_user;

GRANT CREATE, ALTER ON SCHEMA bookapp_dev_schema     TO bookapp_dev_flyway_user;
GRANT CREATE, ALTER ON SCHEMA bookapp_dev_aud_schema TO bookapp_dev_flyway_user;

GRANT SELECT ON ALL TABLES IN SCHEMA bookapp_dev_schema     TO bookapp_dev_flyway_user;
GRANT SELECT ON ALL TABLES IN SCHEMA bookapp_dev_aud_schema TO bookapp_dev_flyway_user;

ALTER DEFAULT PRIVILEGES IN SCHEMA bookapp_dev_schema     GRANT SELECT ON TABLES TO bookapp_dev_flyway_user;
ALTER DEFAULT PRIVILEGES IN SCHEMA bookapp_dev_aud_schema GRANT SELECT ON TABLES TO bookapp_dev_flyway_user;

-- NOTE: DROP, INSERT, UPDATE, DELETE for Flyway can be added later if needed

```

### Flyway migration files
- Path `src/main/resources/db/migration/`
- To create migration files with date and time in the file name
    - E.g. `V20251021_103045__create_table_book.sql`

```
touch src/main/resources/db/migration/V$(date +"%Y%m%d_%H%M%S")__create_table_TODO_PUT_TABLE_NAME_WITHOUT_PREFIX.sql

```


## Build

### Build and run on local

- To build without tests

```
./gradlew clean build -x test
	
```

- To build with tests

```
./gradlew clean build

```

- To run the spring-boot application with `dev` profile


```
./gradlew bootRun --args='--spring.profiles.active=dev'

```
