/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  lacastrillov
 * Created: 15/09/2017
 */

CREATE TABLE `marketplatform`.`configuration_object` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `type` VARCHAR(200) NOT NULL,
  `related_entity` VARCHAR(45) NULL,
  `related_id` INT NULL,
  `data` TEXT NULL,
  PRIMARY KEY (`id`));
