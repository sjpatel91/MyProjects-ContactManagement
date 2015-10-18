CREATE DATABASE `shruti` /*!40100 DEFAULT CHARACTER SET utf8 */;
use shruti;

CREATE TABLE `usermaster` (
  `UserMasterID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`UserMasterID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

INSERT INTO `shruti`.`usermaster` (`UserMasterID`,`Name`)VALUES(1,'Admin');
INSERT INTO `shruti`.`usermaster` (`UserMasterID`,`Name`)VALUES(2,'Employee');


CREATE TABLE `user` (
  `userID` int(11) NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(45) DEFAULT NULL,
  `LastName` varchar(45) DEFAULT NULL,
  `contactNo` varchar(10) DEFAULT NULL,
  `EmailID` varchar(45) DEFAULT NULL,
  `password` varchar(10) DEFAULT NULL,
  `userMasterID` int(11) DEFAULT NULL,
  `active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`userID`),
  KEY `userMasterID_idx` (`userMasterID`),
  CONSTRAINT `userMasterID` FOREIGN KEY (`userMasterID`) REFERENCES `usermaster` (`UserMasterID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

