/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  lacastrillov
 * Created: 23/05/2017
 */

ALTER TABLE `marketplatform`.`web_resource` 
ADD COLUMN `category` VARCHAR(100) NULL AFTER `is_public`;
