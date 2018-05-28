CREATE TABLE users (
  id         INT4         NOT NULL,
  email      VARCHAR(100) NOT NULL,
  password   VARCHAR(24)  NOT NULL,
  first_name VARCHAR(24)  NOT NULL,
  last_name  VARCHAR(24)  NOT NULL,
  phone      VARCHAR(24)  NOT NULL,
  role       VARCHAR(24)  NOT NULL,
  createdAt  TIMESTAMP    NOT NULL,
  CONSTRAINT users_pkey PRIMARY KEY (id),
  CONSTRAINT users_email_uk UNIQUE (email)
);

CREATE TABLE categories (
  id          INT4        NOT NULL,
  name        VARCHAR(32) NOT NULL,
  description TEXT        NULL,
  color       VARCHAR(24) DEFAULT '#ffffff' NOT NULL,
  createdAt   TIMESTAMP   NOT NULL,
  CONSTRAINT category_pkey PRIMARY KEY (id),
  CONSTRAINT category_name UNIQUE (name)
);

CREATE TABLE products (
  id          INT4        NOT NULL,
  name        VARCHAR(32) NOT NULL,
  price       DECIMAL     NOT NULL,
  quantity    INT         NOT NULL,
  seller_id   INT4        NOT NULL,
  category_id INT4        NOT NULL,
  createdAt   TIMESTAMP   NOT NULL,
  CONSTRAINT product_pkey PRIMARY KEY (id)
);

ALTER TABLE products
  ADD CONSTRAINT products_users_fk    FOREIGN KEY (seller_id)   REFERENCES users       (id),
  ADD CONSTRAINT products_category_fk FOREIGN KEY (category_id) REFERENCES categories  (id);

CREATE SEQUENCE IF NOT EXISTS hibernate_sequence
  START WITH 1
  INCREMENT BY 1;
