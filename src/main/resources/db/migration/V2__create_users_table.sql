CREATE TABLE users(
id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
username VARCHAR(50) NOT NULL,
password VARCHAR(70) NOT NULL,
role VARCHAR(10) NOT NULL,
enabled int NOT NULL
);