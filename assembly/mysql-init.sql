CREATE DATABASE IF NOT EXISTS assembly;
CREATE USER 'das'@'localhost' IDENTIFIED BY 'das';
GRANT ALL PRIVILEGES ON mydatabase.* TO 'das'@'localhost';
FLUSH PRIVILEGES;

