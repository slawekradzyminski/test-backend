-- noinspection SqlNoDataSourceInspectionForFile

DROP TABLE IF EXISTS information;
DROP TABLE if exists pet;

CREATE TABLE pet (
    id INTEGER PRIMARY KEY,
    name VARCHAR(250) NOT NULL UNIQUE
);

CREATE TABLE information (
  id IDENTITY PRIMARY KEY,
  name VARCHAR(250) NOT NULL UNIQUE,
  nationality VARCHAR(250) NOT NULL,
  salary INTEGER DEFAULT NULL
);

INSERT INTO pet (id, name) VALUES
    ('1', 'Cow'),
    ('2', 'Dog'),
    ('3', 'Cat');

INSERT INTO information (name, nationality, salary) VALUES
  ('CristianoRonaldo', 'Portugal', 80000),
  ('LeoMessi', 'Argentina', 45000);