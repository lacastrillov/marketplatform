/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  lacastrillov
 * Created: 30/07/2017
 */

ALTER TABLE `marketplatform`.`purchaseorder_detail` 
ADD COLUMN `iva` INT NULL AFTER `discount`;

ALTER TABLE `marketplatform`.`purchase_order` 
ADD COLUMN `iva` INT NULL AFTER `discount`;
