/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  grupot
 * Created: 12/09/2017
 */

ALTER TABLE `marketplatform`.`purchase_order` 
ADD COLUMN `delivery_date` DATETIME NULL AFTER `registration_date`;
