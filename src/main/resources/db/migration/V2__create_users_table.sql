CREATE TABLE Users(
id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
username VARCHAR(50) NOT NULL,
password VARCHAR NOT NULL,
role VARCHAR(10) NOT NULL,
enabled BOOLEAN NOT NULL
);

insert into Users(username, password, role, enabled) values
('user','default','USER',true),
('admin','admin','ADMIN',true);

