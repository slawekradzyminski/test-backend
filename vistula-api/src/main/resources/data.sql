-- noinspection SqlNoDataSourceInspectionForFile

DROP TABLE IF EXISTS information;
DROP TABLE IF EXISTS pet;
DROP TABLE IF EXISTS users;

CREATE TABLE pet (
    id INTEGER PRIMARY KEY,
    name VARCHAR(250) NOT NULL UNIQUE
);

CREATE TABLE users (
  id IDENTITY PRIMARY KEY,
  firstName VARCHAR(250) NOT NULL,
  lastName VARCHAR(250) NOT NULL,
  userName VARCHAR(250),
  salary INTEGER,
  age INTEGER
);

CREATE TABLE information (
  id IDENTITY PRIMARY KEY,
  name VARCHAR(250) NOT NULL UNIQUE,
  nationality VARCHAR(250) NOT NULL,
  salary INTEGER DEFAULT NULL
);

INSERT INTO users (id, firstName, lastName, userName, salary, age) VALUES
    (1, 'Slawomir', 'Radzyminski', 'slawenty', 666, 66),
    (2, 'Gosia', 'Nowak', 'gosianowak123', 999, 33);

INSERT INTO pet (id, name) VALUES
    ('1', 'Cow'),
    ('2', 'Dog'),
    ('3', 'Cat');

INSERT INTO information (name, nationality, salary) VALUES
  ('CristianoRonaldo', 'Portugal', 80000),
  ('LeoMessi', 'Argentina', 45000);