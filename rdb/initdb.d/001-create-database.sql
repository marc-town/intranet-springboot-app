DROP DATABASE IF EXISTS `gsol_app_db`;
CREATE DATABASE `gsol_app_db` default character set utf8mb4;
GRANT ALL ON gsol_app_db.* TO 'apluser'@'%' IDENTIFIED BY 'apluser';
FLUSH PRIVILEGES;
