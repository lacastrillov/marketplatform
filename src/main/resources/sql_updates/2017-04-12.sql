/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  grupot
 * Created: 12/04/2017
 */

CREATE TABLE `marketplatform`.`web_resource` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `pah` VARCHAR(500) NOT NULL,
  `type` VARCHAR(45) NULL,
  `is_public` TINYINT(1) NULL,
  PRIMARY KEY (`id`));


CREATE TABLE `marketplatform`.`authorization` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(200) NOT NULL,
  `description` VARCHAR(255) NULL,
  PRIMARY KEY (`id`));


CREATE TABLE `marketplatform`.`role_authorization` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `role_id` INT NOT NULL,
  `authorization_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_role_authorization_1_idx` (`role_id` ASC),
  INDEX `fk_role_authorization_2_idx` (`authorization_id` ASC),
  CONSTRAINT `fk_role_authorization_1`
    FOREIGN KEY (`role_id`)
    REFERENCES `marketplatform`.`role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_role_authorization_2`
    FOREIGN KEY (`authorization_id`)
    REFERENCES `marketplatform`.`authorization` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


CREATE TABLE `marketplatform`.`webresource_role` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `webresource_id` INT NOT NULL,
  `role_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_webresource_role_1_idx` (`webresource_id` ASC),
  INDEX `fk_webresource_role_2_idx` (`role_id` ASC),
  CONSTRAINT `fk_webresource_role_1`
    FOREIGN KEY (`webresource_id`)
    REFERENCES `marketplatform`.`web_resource` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_webresource_role_2`
    FOREIGN KEY (`role_id`)
    REFERENCES `marketplatform`.`role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


CREATE TABLE `marketplatform`.`webresource_authorization` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `webresource_id` INT NOT NULL,
  `authorization_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_webresource_authorization_1_idx` (`webresource_id` ASC),
  INDEX `fk_webresource_authorization_2_idx` (`authorization_id` ASC),
  CONSTRAINT `fk_webresource_authorization_1`
    FOREIGN KEY (`webresource_id`)
    REFERENCES `marketplatform`.`web_resource` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_webresource_authorization_2`
    FOREIGN KEY (`authorization_id`)
    REFERENCES `marketplatform`.`authorization` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);