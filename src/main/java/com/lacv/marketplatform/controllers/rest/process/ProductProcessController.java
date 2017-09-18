/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lacv.marketplatform.controllers.rest.process;


import com.lacv.marketplatform.dtos.process.BasicResultDto;
import com.lacv.marketplatform.entities.LogProcess;
import com.lacv.marketplatform.entities.User;
import com.lacv.marketplatform.services.LogProcessService;
import com.lacv.marketplatform.services.UserService;
import com.lacv.marketplatform.services.security.SecurityService;
import com.dot.gcpbasedot.annotation.DoProcess;
import com.dot.gcpbasedot.controller.RestProcessController;
import com.lacv.marketplatform.dtos.process.ActivationProductPDto;
import com.lacv.marketplatform.services.mail.MailingService;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author nalvarez
 */
@Controller
@RequestMapping(value="/rest/processProduct")
public class ProductProcessController extends RestProcessController {
    
    @Autowired
    UserService userService;
    
    @Autowired
    LogProcessService logProcessService;
    
    @Autowired
    SecurityService securityService;
    
    @Autowired
    MailingService mailingService;
    
    
    @PostConstruct
    public void init(){
        super.addControlProcess("processProduct", LogProcess.class, logProcessService);
    }
    
    @Override
    public String getClientId(){
        User user= securityService.getCurrentUser();
        return user.getUsername();
    }
    
    @DoProcess
    public BasicResultDto activarProducto(ActivationProductPDto activationProductPDto){
        BasicResultDto result= new BasicResultDto();
        
        Map<String, String> data= new HashMap<>();
        data.put("nombreUsuario", activationProductPDto.getContactUser().getUserName());
        data.put("correoUsuario", activationProductPDto.getContactUser().getMail());
        data.put("numeroCelular", activationProductPDto.getContactUser().getCellPhone());
        String comments= activationProductPDto.getContactUser().getComments();
        comments+="<br><br><b>productId:</b> "+activationProductPDto.getProductId();
        comments+="<br><br><b>productCode:</b> "+activationProductPDto.getProductCode();
        comments+="<br><br><b>brand:</b> "+activationProductPDto.getBrand();
        comments+="<br><br><b>registerDate:</b> "+activationProductPDto.getRegisterDate().toString();
        data.put("comentarios", comments);
        
        boolean sent= mailingService.sendTemplateMail(activationProductPDto.getContactUser().getMail(), "contact_user", "Contacto de Usuario", data);
        
        result.setUsername(activationProductPDto.getContactUser().getMail());
        result.setSuccess(sent);
        if(sent){
            result.setMessage("Producto Activado Correctamente");
        }else{
            result.setMessage("Error al activar el Producto");
        }
        
        return result;
    }
    
}
