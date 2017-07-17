/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  grupot
 * Created: 14/07/2017
 */

CREATE TABLE `marketplatform`.`lead_table` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `table_alias` VARCHAR(100) NOT NULL,
  `description` VARCHAR(200) NULL,
  `status` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));


CREATE TABLE `marketplatform`.`table_column` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `column_alias` VARCHAR(100) NOT NULL,
  `data_type` VARCHAR(45) NOT NULL,
  `field_type` VARCHAR(45) NULL,
  `column_size` INT NULL,
  `width` INT NULL,
  `column_order` INT NULL,
  `default_value` VARCHAR(200) NULL,
  `options` VARCHAR(400) NULL,
  PRIMARY KEY (`id`));

ALTER TABLE `marketplatform`.`table_column` 
ADD COLUMN `lead_table_id` INT NOT NULL AFTER `options`,
ADD INDEX `fk_column_1_idx` (`lead_table_id` ASC);
ALTER TABLE `marketplatform`.`table_column` 
ADD CONSTRAINT `fk_column_1`
  FOREIGN KEY (`lead_table_id`)
  REFERENCES `marketplatform`.`lead_table` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;