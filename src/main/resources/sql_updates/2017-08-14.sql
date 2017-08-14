/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  grupot
 * Created: 14/08/2017
 */

ALTER TABLE `marketplatform`.`user` 
CHANGE COLUMN `name` `first_name` VARCHAR(100) NULL DEFAULT NULL AFTER `id`,
CHANGE COLUMN `email` `email` VARCHAR(100) NULL DEFAULT NULL AFTER `first_name`,
CHANGE COLUMN `username` `username` VARCHAR(100) NULL DEFAULT NULL AFTER `email`,
CHANGE COLUMN `password` `password` VARCHAR(100) NULL DEFAULT NULL AFTER `username`,
CHANGE COLUMN `gender` `gender` VARCHAR(1) NULL DEFAULT NULL AFTER `password`;

ALTER TABLE `marketplatform`.`user` 
ADD COLUMN `last_name` VARCHAR(100) NULL AFTER `first_name`,
ADD COLUMN `id_document` BIGINT NULL AFTER `last_name`,
ADD COLUMN `document_type` VARCHAR(10) NULL AFTER `id_document`,
ADD COLUMN `phone` VARCHAR(45) NULL AFTER `email`,
ADD COLUMN `cell` VARCHAR(45) NULL AFTER `phone`,
ADD COLUMN `address` VARCHAR(200) NULL AFTER `cell`,
ADD COLUMN `city` VARCHAR(100) NULL AFTER `birthday`;
