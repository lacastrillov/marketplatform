/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  grupot
 * Created: 12/07/2017
 */

ALTER TABLE `marketplatform`.`log_process` 
ADD COLUMN `output_data_format` VARCHAR(10) NULL AFTER `data_out`;
