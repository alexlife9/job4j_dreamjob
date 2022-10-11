CREATE TABLE post (
   id SERIAL PRIMARY KEY,
   name VARCHAR(100),
   description TEXT,
   created DATE,
   city VARCHAR(100)
);