DROP TABLE IF EXISTS tb_user;


CREATE TABLE tb_user
(
    id         SERIAL PRIMARY KEY,
    email   VARCHAR(255) NOT NULL UNIQUE,
    password   VARCHAR(255),
    created_at TIMESTAMP    NOT NULL DEFAULT NOW()
);