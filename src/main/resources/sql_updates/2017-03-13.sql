/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  grupot
 * Created: 13/03/2017
 */

ALTER TABLE `marketplatform`.`product` 
DROP COLUMN `image`;

CREATE TABLE `marketplatform`.`product_image` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `image` VARCHAR(200) NOT NULL,
  `order_level` INT NULL DEFAULT 0,
  `product_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_product_image_1_idx` (`product_id` ASC),
  CONSTRAINT `fk_product_image_1`
    FOREIGN KEY (`product_id`)
    REFERENCES `marketplatform`.`product` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE `marketplatform`.`sub_category` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `description` VARCHAR(200) NULL,
  `image` VARCHAR(200) NULL,
  `category_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_sub_category_1_idx` (`category_id` ASC),
  CONSTRAINT `fk_sub_category_1`
    FOREIGN KEY (`category_id`)
    REFERENCES `marketplatform`.`category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

ALTER TABLE `marketplatform`.`product` 
ADD COLUMN `subcategory_id` INT NULL AFTER `category_id`,
ADD INDEX `fk_product_1_idx` (`subcategory_id` ASC);

ALTER TABLE `marketplatform`.`product` 
ADD CONSTRAINT `FK_product_subcategory_id`
  FOREIGN KEY (`subcategory_id`)
  REFERENCES `marketplatform`.`sub_category` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `marketplatform`.`product` 
ADD COLUMN `featured` `featured` TINYINT(4) NULL DEFAULT 0;