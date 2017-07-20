/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  lacastrillov
 * Created: 20/07/2017
 */

ALTER TABLE `marketplatform`.`lead_table` 
ADD COLUMN `file_upload` TINYINT(1) NULL AFTER `description`;
