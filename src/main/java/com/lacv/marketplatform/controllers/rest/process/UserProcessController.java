/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lacv.marketplatform.controllers.rest.process;


import com.lacv.marketplatform.dtos.process.BasicResultDto;
import com.lacv.marketplatform.dtos.process.CreatePasswordDto;
import com.lacv.marketplatform.entities.LogProcess;
import com.lacv.marketplatform.entities.User;
import com.lacv.marketplatform.services.LogProcessService;
import com.lacv.marketplatform.services.UserService;
import com.lacv.marketplatform.services.security.SecurityService;
import com.dot.gcpbasedot.annotation.DoProcess;
import com.dot.gcpbasedot.controller.RestController;
import com.dot.gcpbasedot.util.AESEncrypt;
import com.lacv.marketplatform.constants.WebConstants;
import com.lacv.marketplatform.dtos.process.ContactUserPDto;
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
@RequestMapping(value="/rest/processUser")
public class UserProcessController extends RestController {
    
    @Autowired
    UserService userService;
    
    @Autowired
    LogProcessService logProcessService;
    
    //@Autowired
    SecurityService securityService;
    
    @Autowired
    MailingService mailingService;
    
    AESEncrypt myInstance= AESEncrypt.getDefault(WebConstants.SECURITY_SALT);
    
    @PostConstruct
    public void init(){
        super.addControlProcess("processUser", LogProcess.class, logProcessService);
    }
    
    @Override
    public String getClientId(){
        User user= securityService.getCurrentUser();
        return user.getUsername();
    }
    
    @DoProcess
    public BasicResultDto createPassword(CreatePasswordDto createPassword){
        BasicResultDto result= new BasicResultDto();
        result.setUsername(createPassword.getUsername());
        
        User user= userService.findUniqueByParameter("username", createPassword.getUsername());
        
        result.setSuccess(false);
        if(user!=null){
            if(createPassword.getPassword().equals(createPassword.getConfirmPassword())){
                user.setPassword(myInstance.encrypt(createPassword.getPassword(), WebConstants.SECURITY_SEED_PASSW));
                userService.update(user);
                result.setMessage("La contraseña se ha creado correctamente");
                result.setSuccess(true);
            }else{
                result.setMessage("Las contraseñas no coinciden");
            }
        }else{
            result.setMessage("No se encontro el usuario");
        }
        
        return result;
    }
    
    @DoProcess
    public BasicResultDto contactUser(ContactUserPDto contactUserPDto){
        BasicResultDto result= new BasicResultDto();
        
        Map<String, String> data= new HashMap<>();
        data.put("nombreUsuario", contactUserPDto.getUserName());
        data.put("correoUsuario", contactUserPDto.getMail());
        data.put("numeroCelular", contactUserPDto.getCellPhone());
        data.put("comentarios", contactUserPDto.getComments());
        
        boolean sent= mailingService.sendTemplateMail(contactUserPDto.getMail(), "contact_user", "Contacto de Usuario", data);
        
        result.setUsername(contactUserPDto.getMail());
        result.setSuccess(sent);
        if(sent){
            result.setMessage("Correo enviado correctamente");
        }else{
            result.setMessage("Error al enviar el correo");
        }
        
        return result;
    }
    
}
