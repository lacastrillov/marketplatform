package com.lacv.marketplatform.controllers;

import com.dot.gcpbasedot.dao.Parameters;
import com.lacv.marketplatform.entities.Product;
import com.lacv.marketplatform.services.ProductImageService;
import com.lacv.marketplatform.services.ProductService;
import com.lacv.marketplatform.services.security.SecurityService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
@RequestMapping(value = "/")
public class HomeController {
    
    @Autowired
    SecurityService securityService;
    
    @Autowired
    ProductService productService;
    
    @Autowired
    ProductImageService productImageService;
    
    
    @RequestMapping(value = "/", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView getIndex(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("market/index");
        
        Parameters p0= new Parameters();
        p0.whereEqual("featured", true);
        p0.whereEqual("status", "Publicado");
        p0.orderBy("registerDate", "DESC");
        p0.setMaxResults(12L);
        
        List<Product> lastFeatured = productService.findByParameters(p0);
        for(Product product: lastFeatured){
            product.setProductImageList(productImageService.findByParameter("product", product));
        }
        
        Parameters p1= new Parameters();
        p1.whereEqual("status", "Publicado");
        p1.orderBy("registerDate", "DESC");
        p1.setMaxResults(9L);
        
        List<Product> lastProducts = productService.findByParameters(p1);
        for(Product product: lastProducts){
            product.setProductImageList(productImageService.findByParameter("product", product));
        }
        
        mav.addObject("parametersFeatured", p0);
        mav.addObject("lastFeatured", lastFeatured);
        mav.addObject("lastProducts", lastProducts);

        return mav;
    }
    
}
