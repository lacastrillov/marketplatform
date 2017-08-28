/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  grupot
 * Created: 28/08/2017
 */

ALTER TABLE `marketplatform`.`user` 
ADD COLUMN `registration_date` DATETIME NULL AFTER `verified`;
