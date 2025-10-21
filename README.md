# spring-boot-book-app
Books managing application using Spring Boot

TODO: more information

## Dev DB setup
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
