/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lacv.marketplatform.controllers.rest.process;

import com.dot.gcpbasedot.annotation.DoProcess;
import com.dot.gcpbasedot.controller.RestController;
import com.lacv.marketplatform.constants.WebConstants;
import com.lacv.marketplatform.dtos.process.BasicResultDto;
import com.lacv.marketplatform.dtos.process.ShippingCartItemPDto;
import com.lacv.marketplatform.dtos.process.ShoppingCartPDto;
import com.lacv.marketplatform.entities.LogProcess;
import com.lacv.marketplatform.entities.Product;
import com.lacv.marketplatform.entities.PurchaseOrder;
import com.lacv.marketplatform.entities.PurchaseorderDetail;
import com.lacv.marketplatform.entities.User;
import com.lacv.marketplatform.entities.UserRole;
import com.lacv.marketplatform.services.LogProcessService;
import com.lacv.marketplatform.services.ProductService;
import com.lacv.marketplatform.services.PropertyService;
import com.lacv.marketplatform.services.PurchaseOrderService;
import com.lacv.marketplatform.services.PurchaseorderDetailService;
import com.lacv.marketplatform.services.UserRoleService;
import com.lacv.marketplatform.services.UserService;
import com.lacv.marketplatform.services.security.SecurityService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author lacastrillov
 */
@Controller
@RequestMapping(value="/rest/processPurchaseOrder")
public class PurchaseOrderProcessController extends RestController  {
    
    @Autowired
    PurchaseOrderService purchaseOrderService;
    
    @Autowired
    PurchaseorderDetailService purchaseorderDetailService;
    
    @Autowired
    ProductService productService;
    
    @Autowired
    UserService userService;
    
    PropertyService propertyService;
    
    @Autowired
    UserRoleService userRoleService;
    
    @Autowired
    LogProcessService logProcessService;
    
    @Autowired
    SecurityService securityService;
    
    @PostConstruct
    public void init(){
        super.addControlProcess("processPurchaseOrder", LogProcess.class, logProcessService);
    }
    
    @Override
    public String getClientId(){
        User user= securityService.getCurrentUser();
        return user.getUsername();
    }
    
    @DoProcess
    public BasicResultDto crearOrdenCompra(ShoppingCartPDto shoppingCartPDto){
        BasicResultDto result= new BasicResultDto();
        PurchaseOrder purchaseOrder= new PurchaseOrder();
        User buyerUser= securityService.getCurrentUser();
        boolean isClient= false;
        
        List<UserRole> userRoles= userRoleService.findByParameter("user", buyerUser);
        if(userRoles.size()==1 && userRoles.get(0).getRole().getName().equals(WebConstants.CLIENT_ROLE)){
            isClient= true;
        }
        if(isClient || shoppingCartPDto.getUserId()==null){
            purchaseOrder.setUser(buyerUser);
        }else if(shoppingCartPDto.getUserId()==null){
            buyerUser= userService.findById(shoppingCartPDto.getUserId());
            purchaseOrder.setUser(buyerUser);
        }
        
        Long number= (new Date()).getTime() + buyerUser.getId();
        purchaseOrder.setNumber(number);
        purchaseOrder.setSubTotal(0);
        purchaseOrder.setDiscount(0);
        purchaseOrder.setIva(0);
        purchaseOrder.setTotal(0);
        
        List<PurchaseorderDetail> purchaseorderDetailList= new ArrayList<>();
        for(ShippingCartItemPDto shippingCartItemPDto: shoppingCartPDto.getItems()){
            Product product= productService.findById(shippingCartItemPDto.getProductId());
            
            PurchaseorderDetail purchaseorderDetail= new PurchaseorderDetail();
            purchaseorderDetail.setProduct(product);
            purchaseorderDetail.setQuantity(shippingCartItemPDto.getQuantity());
            purchaseorderDetail.setSubTotal(product.getBuyUnitPrice() * shippingCartItemPDto.getQuantity());
            purchaseorderDetail.setDiscount((product.getBuyUnitPrice() * product.getDiscount() * shippingCartItemPDto.getQuantity()) / 100);
            purchaseorderDetail.setIva(((purchaseorderDetail.getSubTotal() - purchaseorderDetail.getDiscount()) * propertyService.getInteger("IVA")) / 100);
            purchaseorderDetail.setTotal(purchaseorderDetail.getSubTotal() - purchaseorderDetail.getDiscount() + purchaseorderDetail.getIva());
            
            purchaseOrder.setSubTotal(purchaseOrder.getSubTotal() + purchaseorderDetail.getSubTotal());
            purchaseOrder.setDiscount(purchaseOrder.getSubTotal() + purchaseorderDetail.getDiscount());
            purchaseOrder.setIva(purchaseOrder.getIva() + purchaseorderDetail.getIva());
            purchaseOrder.setTotal(purchaseOrder.getSubTotal() - purchaseOrder.getDiscount() + purchaseOrder.getIva());
            
            purchaseorderDetailList.add(purchaseorderDetail);
        }
        purchaseOrder.setPurchaseorderDetailList(purchaseorderDetailList);
        
        purchaseOrderService.create(purchaseOrder);
        
        result.setSuccess(true);
        result.setUsername(buyerUser.getUsername());
        result.setMessage("Orden creada #"+number);
        
        return result;
    }
    
}
