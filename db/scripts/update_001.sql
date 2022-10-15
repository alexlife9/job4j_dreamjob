CREATE TABLE post (
   id SERIAL PRIMARY KEY,
   name VARCHAR(100),
   description TEXT,
   created DATE,
   city_id INT
);