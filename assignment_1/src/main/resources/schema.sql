-- DROP TABLE IF EXISTS CLOTHES;

CREATE TABLE IF NOT EXISTS CLOTHES (
    id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    brand VARCHAR(50) NOT NULL,
    establishment_year INT NOT NULL,
    price DECIMAL(6,2) NOT NULL
);