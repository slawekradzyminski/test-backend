-- noinspection SqlNoDataSourceInspectionForFile

DROP TABLE IF EXISTS users;

CREATE TABLE users (
  id IDENTITY PRIMARY KEY,
  firstName VARCHAR(250) NOT NULL,
  lastName VARCHAR(250) NOT NULL,
  userName VARCHAR(250),
  salary INTEGER,
  age INTEGER
);

INSERT INTO users (id, firstName, lastName, userName, salary, age) VALUES
    (1, 'Slawomir', 'Radzyminski', 'slawenty', 666, 66),
    (2, 'Gosia', 'Nowak', 'gosianowak123', 999, 33);
