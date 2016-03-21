drop database ChocoDB;
create database ChocoDB;
use ChocoDB;
grant all on ChocoDB.* to 'root'@'localhost' identified by 'choco';
CREATE TABLE ChocoDB.`users` (
  `userid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `guid` varchar(36) DEFAULT NULL,
  `token` varchar(32) DEFAULT NULL,
  `status` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

CREATE TABLE ChocoDB.`sessions` (
  `sessionID` varchar(36) DEFAULT NULL,
  `username` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`sessionID`)

) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

