DROP DATABASE IF EXISTS MILKA;
CREATE DATABASE MILKA CHARACTER SET utf8 COLLATE utf8_unicode_ci;
GRANT USAGE ON *.* TO 'MILKA_USER'@'localhost';
DROP USER 'MILKA_USER'@'localhost';
FLUSH PRIVILEGES;
CREATE USER 'MILKA_USER'@'localhost' IDENTIFIED BY 'MILKA_USER';
GRANT ALL ON MILKA.* TO MILKA_USER IDENTIFIED BY 'MILKA_USER';