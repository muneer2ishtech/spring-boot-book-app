CREATE TABLE ${audit_schema_name}.book_aud (
  id            BIGINT        NOT NULL,
  rev           BIGINT        NOT NULL,
  revtype       SMALLINT      NOT NULL,
  rev_timestamp TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP,
  title         VARCHAR(255)      NULL,
  author        VARCHAR(255)      NULL,
  year          SMALLINT          NULL,
  price         NUMERIC(10,2)     NULL,
  PRIMARY KEY(id, rev)
);
