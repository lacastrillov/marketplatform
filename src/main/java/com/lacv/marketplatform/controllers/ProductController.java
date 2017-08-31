package com.lacv.marketplatform.controllers;

import com.dot.gcpbasedot.dao.Parameters;
import com.dot.gcpbasedot.util.FilterQueryJSON;
import com.lacv.marketplatform.entities.Product;
import com.lacv.marketplatform.entities.SubCategory;
import com.lacv.marketplatform.services.ProductImageService;
import com.lacv.marketplatform.services.ProductService;
import com.lacv.marketplatform.services.SubCategoryService;
import com.lacv.marketplatform.services.security.SecurityService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/productos")
public class ProductController {
    
    @Autowired
    ProductService productService;
    
    @Autowired
    SubCategoryService subCategoryService;
    
    @Autowired
    ProductImageService productImageService;
    
    @Autowired
    SecurityService securityService;
    
    
    @RequestMapping(value = "/listado", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView getProductList(@RequestParam(required = false) String filter,
            @RequestParam(required = false) Long limit, @RequestParam(required = false) Long page,
            @RequestParam(required = false) String sort, @RequestParam(required = false) String dir, HttpServletRequest request) {

        ModelAndView mav = new ModelAndView("market/product/products");
        
        Parameters p= new Parameters();

        if (filter != null && !filter.equals("")) {
            p = FilterQueryJSON.processFilters(filter, Product.class);
        }
        p.whereEqual("status", "Publicado");
        
        p.setPage((page != null) ? page : 1L);
        p.setMaxResults((limit != null) ? limit : 9L);
        p.orderBy((sort != null) ? sort : "registerDate", (dir != null) ? dir : "DESC");
        p.orderBy("orderLevel", "ASC");
        
        List<Product> products = productService.findByParameters(p);
        for(Product product: products){
            Parameters p1= new Parameters();
            p1.whereEqual("product", product);
            p1.orderBy("order", "ASC");
            product.setProductImageList(productImageService.findByParameters(p1));
        }
        
        mav.addObject("title", getTitle(filter));
        mav.addObject("queryString", request.getQueryString());
        mav.addObject("parameters", p);
        mav.addObject("products", products);

        return mav;
    }
    
    @RequestMapping(value = "/detalle/{code}", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView getProductDetails(@PathVariable String code) {
        ModelAndView mav = new ModelAndView("market/product/product_details");
        
        Product product= productService.findUniqueByParameter("code", code);
        Parameters p1= new Parameters();
        p1.whereEqual("product", product);
        p1.orderBy("order", "ASC");
        product.setProductImageList(productImageService.findByParameters(p1));
        
        mav.addObject("product", product);
        
        return mav;
    }
    
    private String getTitle(String filter){
        if(filter!=null){
            JSONObject jsonFilter= new JSONObject(filter);
            JSONObject eq= jsonFilter.getJSONObject("eq");
            Integer subCategoryId= eq.getInt("subCategory");

            SubCategory subCategory= subCategoryService.findById(subCategoryId);
            if(subCategory!=null){
                return subCategory.getName();
            }
        }
        return "Listado de productos";
    }
    
}
