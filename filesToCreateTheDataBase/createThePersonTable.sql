USE `contactapp`;
CREATE TABLE genre (
  idperson INT NOT NULL AUTO_INCREMENT,
  lastname VARCHAR(45) NOT NULL,
  firstname VARCHAR(45) NOT NULL,
  nickname VARCHAR(45) NOT NULL,
  phone_number VARCHAR(15) NOT NULL,
  adress VARCHAR(200) NOT NULL,
  emailadress VARCHAR(200) NOT NULL,
  birthdate DATE NULL,
  PRIMARY KEY (idperson));