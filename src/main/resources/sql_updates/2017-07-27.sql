/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  lacastrillov
 * Created: 27/07/2017
 */

ALTER TABLE `marketplatform`.`purchase_order` 
CHANGE COLUMN `total` `total` INT NULL DEFAULT NULL ,
ADD COLUMN `sub_total` INT NULL AFTER `record_time`,
ADD COLUMN `discount` INT NULL AFTER `sub_total`;


ALTER TABLE `marketplatform`.`purchaseorder_detail` 
ADD COLUMN `sub_total` INT NULL AFTER `unit_price`,
ADD COLUMN `discount` INT NULL AFTER `sub_total`,
ADD COLUMN `total` INT NULL AFTER `discount`;
