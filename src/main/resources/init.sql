CREATE TABLE IF NOT EXISTS person
(
    id  IDENTITY NOT NULL PRIMARY KEY,
    first_name VARCHAR(50)     NOT NULL,
    last_name  VARCHAR(100)  NOT NULL,
    birthday_date  DATE             NOT NULL,
    primary key (id)
);

INSERT INTO person(first_name, last_name, birthday_date) values ('firstName' , 'lastName', '2000-12-12');
INSERT INTO person(first_name, last_name, birthday_date) values ('firstName2' , 'lastName2', '1997-12-10');
INSERT INTO person(first_name, last_name, birthday_date) values ('firstName3' , 'lastName3', '1995-09-12');
INSERT INTO person(first_name, last_name, birthday_date) values ('firstName4' , 'lastName4', '1990-05-23');