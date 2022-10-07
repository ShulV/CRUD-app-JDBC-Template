# CRUD-app-1
CRUD app. Tech stack: java spring, thymeleaf, HTML, CSS, postgres, JDBC Template

# Код генерации БД (DB generation SQL script):

CREATE TABLE people(
    id integer NOT NULL GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    "name" varchar(50) NOT NULL,
    patronymic varchar(50) NOT NULL,
    surname varchar(50) NOT NULL,
    birthday date NOT NULL
);


CREATE TABLE books(
    id integer NOT NULL GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    "name" varchar(100) NOT NULL,
    author varchar(100) NOT NULL,
    date date NOT NULL,
    people_id int NOT NULL REFERENCES people(id) ON DELETE Restrict ON UPDATE Cascade
);





