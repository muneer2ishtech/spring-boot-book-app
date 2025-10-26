# spring-boot-book-app
Books managing application using Spring Boot

## Tech stack
- JDK:25
- Spring-boot:3.5.6
- PostgreSql:18
- Flyway for DB Migration


TODO: more information

## Database

### Dev DB setup
- Run following for first time before launching spring-boot-boot-app after PostgreSql is started

```
\connect ishtech_dev_db;

-- ===== Common =====
CREATE USER bookapp_dev_user        PASSWORD 'bookapp_dev_pass'        NOSUPERUSER;
CREATE USER bookapp_dev_flyway_user PASSWORD 'bookapp_dev_flyway_pass' NOSUPERUSER;

GRANT CONNECT ON DATABASE ishtech_dev_db TO bookapp_dev_user;
GRANT CONNECT ON DATABASE ishtech_dev_db TO bookapp_dev_flyway_user;

CREATE SCHEMA bookapp_dev_schema;
CREATE SCHEMA bookapp_dev_aud_schema;

-- ===== App User =====
GRANT USAGE ON SCHEMA public                 TO bookapp_dev_user;
GRANT USAGE ON SCHEMA bookapp_dev_schema     TO bookapp_dev_user;
GRANT USAGE ON SCHEMA bookapp_dev_aud_schema TO bookapp_dev_user;

-- For existing tables
GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA bookapp_dev_schema     TO bookapp_dev_user;
GRANT SELECT, INSERT                 ON ALL TABLES IN SCHEMA bookapp_dev_aud_schema TO bookapp_dev_user;

-- For existing sequences
GRANT USAGE, SELECT, UPDATE ON ALL SEQUENCES IN SCHEMA bookapp_dev_schema     TO bookapp_dev_user;
GRANT USAGE, SELECT, UPDATE ON ALL SEQUENCES IN SCHEMA bookapp_dev_aud_schema TO bookapp_dev_user;

-- For future tables and sequences created by bookapp_dev_flyway_user
ALTER DEFAULT PRIVILEGES FOR ROLE bookapp_dev_flyway_user IN SCHEMA bookapp_dev_schema     GRANT SELECT, INSERT, UPDATE, DELETE ON TABLES TO bookapp_dev_user;
ALTER DEFAULT PRIVILEGES FOR ROLE bookapp_dev_flyway_user IN SCHEMA bookapp_dev_aud_schema GRANT SELECT, INSERT                 ON TABLES TO bookapp_dev_user;

ALTER DEFAULT PRIVILEGES FOR ROLE bookapp_dev_flyway_user IN SCHEMA bookapp_dev_schema     GRANT USAGE, SELECT, UPDATE ON SEQUENCES TO bookapp_dev_user;
ALTER DEFAULT PRIVILEGES FOR ROLE bookapp_dev_flyway_user IN SCHEMA bookapp_dev_aud_schema GRANT USAGE, SELECT, UPDATE ON SEQUENCES TO bookapp_dev_user;

-- ===== Flyway User =====
GRANT USAGE         ON SCHEMA public                 TO bookapp_dev_flyway_user;
GRANT USAGE, CREATE ON SCHEMA bookapp_dev_schema     TO bookapp_dev_flyway_user;
GRANT USAGE, CREATE ON SCHEMA bookapp_dev_aud_schema TO bookapp_dev_flyway_user;

-- For existing tables
GRANT SELECT ON ALL TABLES IN SCHEMA bookapp_dev_schema     TO bookapp_dev_flyway_user;
GRANT SELECT ON ALL TABLES IN SCHEMA bookapp_dev_aud_schema TO bookapp_dev_flyway_user;

-- For existing sequences
GRANT USAGE, SELECT ON ALL SEQUENCES IN SCHEMA bookapp_dev_schema     TO bookapp_dev_flyway_user;
GRANT USAGE, SELECT ON ALL SEQUENCES IN SCHEMA bookapp_dev_aud_schema TO bookapp_dev_flyway_user;

-- For future tables and sequences created by bookapp_dev_flyway_user
ALTER DEFAULT PRIVILEGES FOR ROLE bookapp_dev_flyway_user IN SCHEMA bookapp_dev_schema     GRANT SELECT ON TABLES TO bookapp_dev_flyway_user;
ALTER DEFAULT PRIVILEGES FOR ROLE bookapp_dev_flyway_user IN SCHEMA bookapp_dev_aud_schema GRANT SELECT ON TABLES TO bookapp_dev_flyway_user;

ALTER DEFAULT PRIVILEGES FOR ROLE bookapp_dev_flyway_user IN SCHEMA bookapp_dev_schema     GRANT USAGE, SELECT ON SEQUENCES TO bookapp_dev_flyway_user;
ALTER DEFAULT PRIVILEGES FOR ROLE bookapp_dev_flyway_user IN SCHEMA bookapp_dev_aud_schema GRANT USAGE, SELECT ON SEQUENCES TO bookapp_dev_flyway_user;

-- Note: DROP, INSERT, UPDATE, DELETE for Flyway can be added later if needed
-- Note: CREATE USER is alias for CREATE ROLE WITH LOGIN

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
