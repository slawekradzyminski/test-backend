-- noinspection SqlNoDataSourceInspectionForFile

DROP TABLE IF EXISTS users;

CREATE TABLE users (
  id IDENTITY PRIMARY KEY,
  firstName VARCHAR(250) NOT NULL,
  lastName VARCHAR(250) NOT NULL,
  userName VARCHAR(250) NOT NULL,
  password VARCHAR(250) NOT NULL
);

INSERT INTO users (id, firstName, lastName, userName, password) VALUES
    (1, 'Slawomir', 'Radzyminski', 'slawenty', 'password'),
    (2, 'Gosia', 'Nowak', 'gosianowak123', 'password');
