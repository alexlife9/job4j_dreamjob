CREATE TABLE IF NOT EXISTS cities
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS post
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(100),
    description TEXT,
    created     DATE,
    city_id     INT REFERENCES cities (id)
);

CREATE TABLE IF NOT EXISTS candidate
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(100),
    description TEXT,
    created     DATE,
    city_id     INT REFERENCES cities (id)
);

CREATE TABLE IF NOT EXISTS users
(
    id       SERIAL PRIMARY KEY,
    name     VARCHAR(100) UNIQUE,
    email    VARCHAR(100) UNIQUE,
    password TEXT
);