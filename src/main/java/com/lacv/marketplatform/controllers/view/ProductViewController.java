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
import com.lacv.marketplatform.entities.Product;
import com.lacv.marketplatform.entities.ProductImage;
import com.lacv.marketplatform.services.ProductImageService;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    @RequestMapping(value = "/component/product-list", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView getProductList(@RequestParam(required = false) Long subCategoryId,
            @RequestParam(required = false) Long limit, @RequestParam(required = false) Long page,
            @RequestParam(required = false) String sort, @RequestParam(required = false) String dir) {

        ModelAndView mav = new ModelAndView("market/product/product_list");

        Parameters p = new Parameters();
        if (subCategoryId != null) {
            p.whereEqual("subCategory.id", subCategoryId);
        }
        p.setMaxResults((limit != null) ? limit : 60L);
        p.setPage((page != null) ? page : 1L);
        p.orderBy((sort != null) ? sort : "registerDate", (dir != null) ? dir : "DESC");

        List<Product> products = productService.findByParameters(p);
        for(Product product: products){
            product.setProductImageList(productImageService.findByParameter("product", product));
        }
        mav.addObject("products", products);

        return mav;
    }

}
