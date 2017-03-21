/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lacv.marketplatform.controllers.view;

import com.lacv.marketplatform.dtos.ProductDto;
import com.lacv.marketplatform.mappers.ProductMapper;
import com.lacv.marketplatform.services.ProductService;
import com.dot.gcpbasedot.controller.ExtViewController;
import com.dot.gcpbasedot.controller.MenuComponent;
import com.dot.gcpbasedot.dao.Parameters;
import com.dot.gcpbasedot.dto.MenuItem;
import com.dot.gcpbasedot.dto.ViewConfig;
import com.dot.gcpbasedot.util.FilterQueryJSON;
import com.lacv.marketplatform.entities.Product;
import com.lacv.marketplatform.entities.ProductImage;
import com.lacv.marketplatform.entities.SubCategory;
import com.lacv.marketplatform.services.ProductImageService;
import com.lacv.marketplatform.services.SubCategoryService;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author lacastrillov
 */
@Controller
@RequestMapping(value = "/vista/product")
public class ProductViewController extends ExtViewController {

    @Autowired
    ProductService productService;
    
    @Autowired
    SubCategoryService subCategoryService;
    
    @Autowired
    ProductImageService productImageService;

    @Autowired
    MenuComponent menuComponent;

    @Autowired
    ProductMapper productMapper;

    
    @PostConstruct
    public void init() {
        ViewConfig view = new ViewConfig("product", "name", productService, ProductDto.class);
        view.setSingularEntityTitle("Producto");
        view.setPluralEntityTitle("Productos");
        view.addChildExtView("productImage", ProductImage.class, ViewConfig.TCV_STANDARD);
        view.addComboboxChildDependent("category", "subCategory");
        super.addControlMapping(view);

        MenuItem menuItem = new MenuItem("Productos", "product", "Gestionar Productos");
        menuComponent.addItemMenu(menuItem);
        super.addMenuComponent(menuComponent);
    }

    @RequestMapping(value = "/product-list", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView getProductList(@RequestParam(required = false) String filter,
            @RequestParam(required = false) Long limit, @RequestParam(required = false) Long page,
            @RequestParam(required = false) String sort, @RequestParam(required = false) String dir, HttpServletRequest request) {

        ModelAndView mav = new ModelAndView("market/product/products");
        
        Parameters p= new Parameters();

        if (filter != null && !filter.equals("")) {
            p = FilterQueryJSON.processFilters(filter, Product.class);
        }
        
        p.setPage((page != null) ? page : 1L);
        p.setMaxResults((limit != null) ? limit : 9L);
        p.orderBy((sort != null) ? sort : "registerDate", (dir != null) ? dir : "DESC");
        
        List<Product> products = productService.findByParameters(p);
        for(Product product: products){
            product.setProductImageList(productImageService.findByParameter("product", product));
        }
        
        mav.addObject("title", getTitle(filter));
        mav.addObject("queryString", request.getQueryString());
        mav.addObject("parameters", p);
        mav.addObject("products", products);

        return mav;
    }
    
    @RequestMapping(value = "/detalle-producto/{code}", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView getProductDetails(PathVariable code) {
        ModelAndView mav = new ModelAndView("market/product/product_details");
        
        Product product= productService.findUniqueByParameter("code", code);
        product.setProductImageList(productImageService.findByParameter("product", product));
        
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
