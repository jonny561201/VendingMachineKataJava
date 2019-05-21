CREATE TABLE products (
    ID serial PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    location VARCHAR(50) NOT NULL,
    cost money NOT NULL
);