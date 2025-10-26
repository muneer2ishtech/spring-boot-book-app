CREATE TABLE book (
  id     BIGSERIAL     PRIMARY KEY,
  title  VARCHAR(255)  NOT NULL,
  author VARCHAR(255)  NOT NULL,
  year   SMALLINT      NOT NULL,
  price  NUMERIC(10,2) NOT NULL,
  CONSTRAINT uk_book_title_author UNIQUE (title, author)
);
