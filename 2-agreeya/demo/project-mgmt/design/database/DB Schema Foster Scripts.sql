SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema Foster
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema Foster
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Foster` DEFAULT CHARACTER SET utf8 ;
USE `Foster` ;

-- -----------------------------------------------------
-- Table `Foster`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Foster`.`role` (
  `RoleID` INT NOT NULL,
  `RoleName` VARCHAR(60) NULL,
  `Status` VARCHAR(1) BINARY NULL,
  PRIMARY KEY (`RoleID`))
ENGINE = InnoDB;

CREATE UNIQUE INDEX `RoleName_UNIQUE` ON `Foster`.`role` (`RoleName` ASC);


-- -----------------------------------------------------
-- Table `Foster`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Foster`.`user` (
  `UserID` INT NOT NULL,
  `UserName` VARCHAR(128) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `useremail` VARCHAR(128) NOT NULL,
  `homestudy` VARCHAR(1) NOT NULL,
  `training` VARCHAR(1) NOT NULL,
  `status` VARCHAR(1) NOT NULL,
  `RoleID` INT NULL,
  `CreatedOn` DATETIME NOT NULL,
  `CreatedBy` INT NOT NULL,
  `ModifiedOn` DATETIME NULL,
  `ModifiedBy` INT NULL,
  PRIMARY KEY (`UserID`),
  CONSTRAINT `FK_USER_ROLE_ROLEID`
    FOREIGN KEY (`RoleID`)
    REFERENCES `Foster`.`role` (`RoleID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE UNIQUE INDEX `UserName_UNIQUE` ON `Foster`.`user` (`UserName` ASC);

CREATE INDEX `FK_USER_ROLE_ROLEID_idx` ON `Foster`.`user` (`RoleID` ASC);


-- -----------------------------------------------------
-- Table `Foster`.`userdetail`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Foster`.`userdetail` (
  `UserID` INT NOT NULL,
  `FirstName` VARCHAR(60) NOT NULL,
  `LastName` VARCHAR(60) NULL,
  `ContactNo` VARCHAR(15) NULL,
  `DOB` DATE NOT NULL,
  `Gender` VARCHAR(1) NOT NULL,
  `MaritalStatus` VARCHAR(1) NOT NULL,
  `Race` VARCHAR(45) NULL,
  `Religion` VARCHAR(45) NULL,
  `Occupation` VARCHAR(60) NULL,
  `Preference` VARCHAR(45) NULL,
  `Hobbies` VARCHAR(256) NULL,
  `SpouseID` INT NULL,
  `Income` VARCHAR(15) NULL,
  `CreatedOn` DATETIME NULL,
  `CreatedBy` INT NULL,
  `ModifiedOn` DATETIME NULL,
  `ModifiedBy` INT NULL,
  CONSTRAINT `UserDetail_User_UserID`
    FOREIGN KEY (`UserID`)
    REFERENCES `Foster`.`user` (`UserID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE UNIQUE INDEX `UserID_UNIQUE` ON `Foster`.`userdetail` (`UserID` ASC);


-- -----------------------------------------------------
-- Table `Foster`.`userfamily`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Foster`.`userfamily` (
  `UserID` INT NOT NULL,
  `Description` LONGTEXT NULL,
  `Kid` VARCHAR(1) NULL,
  `KidsInfo` LONGTEXT NULL,
  `CreatedOn` DATETIME NOT NULL,
  `CreatedBy` INT NOT NULL,
  `ModifiedOn` DATETIME NULL,
  `ModifiedBy` INT NULL,
  CONSTRAINT `userfamily_user_userid`
    FOREIGN KEY (`UserID`)
    REFERENCES `Foster`.`user` (`UserID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE UNIQUE INDEX `userid_UNIQUE` ON `Foster`.`userfamily` (`UserID` ASC);


-- -----------------------------------------------------
-- Table `Foster`.`userspouse`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Foster`.`userspouse` (
  `UserSpouseID` INT NOT NULL,
  `UserID` INT NOT NULL,
  `FirstName` VARCHAR(60) NOT NULL,
  `LastName` VARCHAR(60) NULL,
  `ContactNo` VARCHAR(15) NULL,
  `DOB` DATE NOT NULL,
  `Gender` VARCHAR(1) NOT NULL,
  `MaritalStatus` VARCHAR(1) NOT NULL,
  `Race` VARCHAR(45) NULL,
  `Religion` VARCHAR(45) NULL,
  `Occupation` VARCHAR(60) NULL,
  `Preference` VARCHAR(45) NULL,
  `Hobbies` VARCHAR(256) NULL,
  `Income` VARCHAR(15) NULL,
  `CreatedOn` DATETIME NULL,
  `CreatedBy` INT NULL,
  `ModifiedOn` DATETIME NULL,
  `ModifiedBy` INT NULL,
  `status` VARCHAR(1) NOT NULL,
  PRIMARY KEY (`UserSpouseID`),
  CONSTRAINT `UserSpouse_User_UserID`
    FOREIGN KEY (`UserID`)
    REFERENCES `Foster`.`user` (`UserID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Foster`.`userlicence`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Foster`.`userlicence` (
  `UserID` INT NULL,
  `LicenceNo` VARCHAR(45) NOT NULL,
  `DateOfIssue` DATE NULL,
  `AgencyContact` VARCHAR(15) NULL,
  `AgencyWorker` INT NULL,
  `Createdon` DATETIME NOT NULL,
  `CreatedBy` INT NOT NULL,
  `ModifiedOn` DATETIME NULL,
  `ModifiedBy` INT NULL,
  CONSTRAINT `userlicence_user_userid`
    FOREIGN KEY (`UserID`)
    REFERENCES `Foster`.`user` (`UserID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE UNIQUE INDEX `Userid_UNIQUE` ON `Foster`.`userlicence` (`UserID` ASC);


-- -----------------------------------------------------
-- Table `Foster`.`userinbox`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Foster`.`userinbox` (
  `userid` INT NOT NULL,
  `mailfrom` VARCHAR(128) NULL,
  `mailto` VARCHAR(500) NULL,
  `mailcc` VARCHAR(500) NULL,
  `subject` VARCHAR(500) NULL,
  `mailbody` LONGTEXT NULL,
  `recieveddate` DATETIME NULL,
  `userinboxid` INT NOT NULL,
  `sentcaseid` VARCHAR(45) NULL,
  `mailread` VARCHAR(1) NOT NULL,
  PRIMARY KEY (`userinboxid`),
  CONSTRAINT `userinbox_user_userid`
    FOREIGN KEY (`userid`)
    REFERENCES `Foster`.`user` (`UserID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `userinbox_user_userid_idx` ON `Foster`.`userinbox` (`userid` ASC);


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
