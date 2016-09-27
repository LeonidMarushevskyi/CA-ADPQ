/*Sample profile service related ddl*/

/*DROP DATABASE `fostercare_schema`;*/

CREATE DATABASE `fostercare_schema`;

/*Drop table babu_schema.profile;*/

CREATE TABLE fostercare_schema.profile (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

