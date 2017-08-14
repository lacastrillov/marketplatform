/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lacv.marketplatform.controllers.view;

import com.lacv.marketplatform.dtos.ProductDto;
import com.lacv.marketplatform.services.ProductService;
import com.dot.gcpbasedot.controller.ExtEntityController;
import com.dot.gcpbasedot.components.MenuComponent;
import com.dot.gcpbasedot.dto.MenuItem;
import com.dot.gcpbasedot.dto.ProcessButton;
import com.dot.gcpbasedot.dto.config.ReportConfig;
import com.dot.gcpbasedot.dto.config.EntityConfig;
import com.dot.gcpbasedot.enums.PageType;
import com.lacv.marketplatform.dtos.process.ActivationProductPDto;
import com.lacv.marketplatform.dtos.reports.ProductReportDto;
import com.lacv.marketplatform.entities.ProductImage;
import com.lacv.marketplatform.services.security.SecurityService;
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
@RequestMapping(value = "/vista/product")
public class ProductViewController extends ExtEntityController {

    @Autowired
    ProductService productService;

    @Autowired
    MenuComponent menuComponent;
    
    @Autowired
    SecurityService securityService;

    
    @PostConstruct
    public void init() {
        EntityConfig view = new EntityConfig("product", "name", productService, ProductDto.class);
        view.setSingularEntityTitle("Producto");
        view.setPluralEntityTitle("Productos");
        view.addChildExtView("productImage", ProductImage.class, EntityConfig.TCV_STANDARD);
        view.addComboboxChildDependent("category", "subCategory");
        super.addControlMapping(view);

        MenuItem menuItem = new MenuItem("Productos", "product", "Gestionar Productos");
        menuComponent.addItemMenu(menuItem);
        
        ReportConfig report = new ReportConfig("product", "reporteProductos", productService, ProductReportDto.class);
        report.setPluralReportTitle("Reporte de Productos");
        report.addChildExtReport("productImage", "reporteImagenesProducto", "product_id");
        report.setMaxResultsPerPage(100L);
        report.setDefaultOrderBy("id");
        report.setDefaultOrderDir("DESC");
        
        ProcessButton setPasswordButton= new ProcessButton();
        setPasswordButton.setMainProcessRef("processProduct");
        setPasswordButton.setProcessName("activarProducto");
        setPasswordButton.setProcessTitle("Activar Producto");
        setPasswordButton.addSourceByDestinationField("id", "productId");
        setPasswordButton.addSourceByDestinationField("code", "productCode");
        setPasswordButton.addSourceByDestinationField("brand", "brand");
        setPasswordButton.addSourceByDestinationField("registerDate", "registerDate");
        setPasswordButton.setDtoClass(ActivationProductPDto.class);
        report.addProcessButton(setPasswordButton);
        
        super.addReportMapping(report);
        
        MenuItem menuItem2 = new MenuItem("Productos", "product", "Reporte de Productos");
        menuItem2.setReportName("reporteProductos");
        menuItem2.setPageType(PageType.REPORT);
        menuComponent.addItemMenu(menuItem2);
        
        super.addMenuComponent(menuComponent);
    }
    
    @Override
    public List<MenuItem> configureVisibilityMenu(List<MenuItem> menuData){
        return securityService.configureVisibilityMenu(menuData);
    }

}
