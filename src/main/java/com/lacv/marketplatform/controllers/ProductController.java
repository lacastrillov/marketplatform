package com.lacv.marketplatform.controllers;

import com.dot.gcpbasedot.dao.Parameters;
import com.dot.gcpbasedot.util.FilterQueryJSON;
import com.lacv.marketplatform.entities.Product;
import com.lacv.marketplatform.entities.SubCategory;
import com.lacv.marketplatform.services.ProductImageService;
import com.lacv.marketplatform.services.ProductService;
import com.lacv.marketplatform.services.SubCategoryService;
import com.lacv.marketplatform.services.security.SecurityService;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
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
        products.forEach((product) -> {
            Parameters p1= new Parameters();
            p1.whereEqual("product", product);
            p1.orderBy("order", "ASC");
            product.setProductImageList(productImageService.findByParameters(p1));
        });
        
        mav.addObject("title", getTitle(p));
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
        
        Parameters p2= new Parameters();
        p2.whereEqual("subCategory", product.getSubCategory());
        p2.whereEqual("status", "Publicado");
        p2.whereDifferentThan("id", product.getId());
        p2.orderBy("registerDate", "DESC");
        p2.setMaxResults(8L);
        
        List<Product> relatedProducts= productService.findByParameters(p2);
        
        if(relatedProducts.size()<8){
            p2.getEqualParameters().remove("subCategory");
            p2.whereEqual("category", product.getCategory());
            if(relatedProducts.size()>0){
                List<Integer> listIds= new ArrayList<>();
                relatedProducts.forEach((rProduct) -> {
                    listIds.add(rProduct.getId());
                });
                p2.whereNotIn("id", listIds.toArray());
            }
            p2.setMaxResults(8L-relatedProducts.size());
            
            List<Product> complementsProducts= productService.findByParameters(p2);
            complementsProducts.forEach((cProduct) -> {
                relatedProducts.add(cProduct);
            });
        }
        if(relatedProducts.size()>0){
            relatedProducts.forEach((rProduct) -> {
                Parameters p3= new Parameters();
                p3.whereEqual("product", rProduct);
                p3.orderBy("order", "ASC");
                rProduct.setProductImageList(productImageService.findByParameters(p3));
            });
        }
        
        mav.addObject("product", product);
        mav.addObject("relatedProducts", relatedProducts);
        
        return mav;
    }
    
    private String getTitle(Parameters p){
        if(p.getEqualParameters().size()>0){
            if(p.getEqualParameters().containsKey("subCategory")){
                Integer subCategoryId= ((SubCategory)p.getEqualParameters().get("subCategory")[1]).getId();
                SubCategory subCategory= subCategoryService.findById(subCategoryId);
                if(subCategory!=null){
                    return subCategory.getName();
                }
            }
        }else if(p.getLikeParameters().size()>0){
            if(p.getLikeParameters().containsKey("name")){
                return p.getLikeParameters().get("name");
            }
        }
        return "Listado de productos";
    }
    
}
