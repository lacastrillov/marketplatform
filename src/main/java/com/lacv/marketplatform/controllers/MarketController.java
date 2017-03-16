package com.lacv.marketplatform.controllers;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/tienda")
public class MarketController {
 
    @RequestMapping(value = "/", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView getIndex(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("market/index");

        return mav;
    }
    
    @RequestMapping(value = "/comparar", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView getCompair(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("market/product/compair");

        return mav;
    }
    
    @RequestMapping(value = "/componentes", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView getComponents() {

        ModelAndView mav = new ModelAndView("market/components");
        
        return mav;
    }

    @RequestMapping(value = "/contactanos", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView getContact() {
        ModelAndView mav = new ModelAndView("market/contact");
        return mav;
    }
    
    @RequestMapping(value = "/foro", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView getFac() {
        ModelAndView mav = new ModelAndView("market/faq");
        return mav;
    }
    
    @RequestMapping(value = "/recuperar-clave", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView getForgetPass() {
        ModelAndView mav = new ModelAndView("market/forgetpass");
        return mav;
    }
    
    @RequestMapping(value = "/politicas-de-privacidad", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView getLegalNotice() {
        ModelAndView mav = new ModelAndView("market/legal_notice");
        return mav;
    }
    
    @RequestMapping(value = "/login", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView getLogin() {
        ModelAndView mav = new ModelAndView("market/login");
        return mav;
    }
    
    @RequestMapping(value = "/normal", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView getNormal() {
        ModelAndView mav = new ModelAndView("market/normal");
        return mav;
    }
    
    @RequestMapping(value = "/detalle-producto", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView getProductDetail() {
        ModelAndView mav = new ModelAndView("market/product/product_details");
        return mav;
    }
    
    @RequestMapping(value = "/carrito-de-compras", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView getProductSumary() {
        ModelAndView mav = new ModelAndView("market/product/product_summary");
        return mav;
    }
    
    @RequestMapping(value = "/productos", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView getProducts() {
        ModelAndView mav = new ModelAndView("market/product/products");
        return mav;
    }
    
    @RequestMapping(value = "/registro", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView getRegister() {
        ModelAndView mav = new ModelAndView("market/register");
        return mav;
    }
    
    @RequestMapping(value = "/promocion", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView getSpecialOffer() {
        ModelAndView mav = new ModelAndView("market/special_offer");
        return mav;
    }
    
    @RequestMapping(value = "/terminos-y-condiciones", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView getTac() {
        ModelAndView mav = new ModelAndView("market/tac");
        return mav;
    }
    

}
