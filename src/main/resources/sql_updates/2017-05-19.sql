/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  grupot
 * Created: 19/05/2017
 */

ALTER TABLE `marketplatform`.`role` 
ADD COLUMN `home_page` VARCHAR(200) NULL AFTER `name`,
ADD COLUMN `priority_check` INT NULL DEFAULT 0 AFTER `home_page`;
