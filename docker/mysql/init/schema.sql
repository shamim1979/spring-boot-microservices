# create databases
CREATE DATABASE IF NOT EXISTS `user`;
CREATE DATABASE IF NOT EXISTS `utility`;

# create root user and grant rights
# CREATE USER 'root'@'localhost' IDENTIFIED BY 'root';
GRANT ALL PRIVILEGES ON *.* TO 'root'@'%';