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
import com.dot.gcpbasedot.controller.RestProcessController;
import com.dot.gcpbasedot.util.AESEncrypt;
import com.lacv.marketplatform.components.WebConstants;
import com.lacv.marketplatform.dtos.process.ContactUserPDto;
import com.lacv.marketplatform.dtos.process.RegisterUserPDto;
import com.lacv.marketplatform.entities.Role;
import com.lacv.marketplatform.entities.UserRole;
import com.lacv.marketplatform.services.PropertyService;
import com.lacv.marketplatform.services.RoleService;
import com.lacv.marketplatform.services.UserRoleService;
import com.lacv.marketplatform.services.mail.MailingService;
import java.util.Date;
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
public class UserProcessController extends RestProcessController {
    
    @Autowired
    UserService userService;
    
    @Autowired
    RoleService roleService;
    
    @Autowired
    UserRoleService userRoleService;
    
    @Autowired
    LogProcessService logProcessService;
    
    @Autowired
    SecurityService securityService;
    
    @Autowired
    MailingService mailingService;
    
    @Autowired
    PropertyService propertyService;
    
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
        
        String contactRecipient= propertyService.getString("CONTACT_RECIPIENT");
        boolean sent= mailingService.sendTemplateMail(contactRecipient, "contact_user", "Contacto de Usuario", data);
        
        result.setUsername(contactUserPDto.getMail());
        result.setSuccess(sent);
        if(sent){
            result.setMessage("Tu mensaje ha sido enviado, pronto nos pondremos en contacto contigo");
        }else{
            result.setMessage("Error al enviar el correo");
        }
        
        return result;
    }
    
    @DoProcess
    public BasicResultDto registerUser(RegisterUserPDto registerUserPDto){
        BasicResultDto result= new BasicResultDto();
        
        User user= userService.findUniqueByParameter("email", registerUserPDto.getEmail());
        result.setUsername(registerUserPDto.getEmail());
        if(user==null){
            user= new User();
            user.setFirstName(registerUserPDto.getFirstName());
            user.setLastName(registerUserPDto.getLastName());
            user.setCell(registerUserPDto.getCell());
            user.setEmail(registerUserPDto.getEmail());
            user.setPassword(myInstance.encrypt(registerUserPDto.getPassword(), WebConstants.SECURITY_SEED_PASSW));
            user.setRegistrationDate(new Date());
            user.setFailedAttempts(0);
            user.setUsername(registerUserPDto.getEmail());
            user.setVerified(false);
            user.setStatus("Active");
            
            userService.create(user);
            
            Role role= roleService.findUniqueByParameter("name", WebConstants.CLIENT_ROLE);
            UserRole userRole= new UserRole();
            userRole.setUser(user);
            userRole.setRole(role);
            userRoleService.create(userRole);
            
            result.setSuccess(true);
            result.setMessage("Te has registrado correctamente, ahora puedes iniciar sesi&oacute;n en tu cuenta...");
        }else{
            result.setSuccess(false);
            result.setMessage("El usuario no se puede crear porque ya existe otro con el mismo correo electr&oacute;nico!!!");
        }
        
        return result;
    }
    
}
