/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lacv.marketplatform.controllers.view;

import com.lacv.marketplatform.dtos.CategoryDto;
import com.lacv.marketplatform.mappers.CategoryMapper;
import com.lacv.marketplatform.services.CategoryService;
import com.dot.gcpbasedot.controller.ExtViewController;
import com.dot.gcpbasedot.controller.MenuComponent;
import com.dot.gcpbasedot.dto.MenuItem;
import com.dot.gcpbasedot.dto.ViewConfig;
import com.lacv.marketplatform.entities.Category;
import com.lacv.marketplatform.entities.SubCategory;
import com.lacv.marketplatform.services.SubCategoryService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author lacastrillov
 */
@Controller
@RequestMapping(value="/vista/category")
public class CategoryViewController extends ExtViewController {
    
    @Autowired
    CategoryService categoryService;
    
    @Autowired
    SubCategoryService subCategoryService;
    
    @Autowired
    MenuComponent menuComponent;
    
    @Autowired
    CategoryMapper categoryMapper;
    
    
    @PostConstruct
    public void init(){
        ViewConfig view= new ViewConfig("category", "name", categoryService, CategoryDto.class);
        view.setSingularEntityTitle("Categoria");
        view.setPluralEntityTitle("Categorias");
        view.setMultipartFormData(true);
        view.addChildExtView("subCategory", SubCategory.class, ViewConfig.TCV_STANDARD);
        view.addInternalViewButton("product", "Ver productos");
        super.addControlMapping(view);
        
        MenuItem menuItem= new MenuItem("Productos", "category", "Gestionar Categorias");
        menuItem.setParentPosition(7);
        menuComponent.addItemMenu(menuItem);
        super.addMenuComponent(menuComponent);
    }
    
    @RequestMapping(value = "/component/menu-categories-subcategories", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView getMenuCategoriesSubcategories() {
        ModelAndView mav = new ModelAndView("market/menu_categories_subcategories");
        
        List<Category> categories= categoryService.listAll();
        for(Category category: categories){
            category.setSubCategoryList(subCategoryService.findByParameter("category", category));
        }
        
        Map<Integer, Long> countProductsByCategories= new HashMap<>();
        List<Map<String, Object>> listCountProductsByCategories= categoryService.findAllMapEntities("countProductsByCategories", new String[]{}, new Object[]{});
        for(Map<String, Object> item: listCountProductsByCategories){
            countProductsByCategories.put((Integer)item.get("category_id"), (Long)item.get("count"));
        }
        
        Map<Integer, Long> countProductsBySubcategories= new HashMap<>();
        List<Map<String, Object>> listCountProductsBySubcategories= categoryService.findAllMapEntities("countProductsBySubcategories", new String[]{}, new Object[]{});
        for(Map<String, Object> item: listCountProductsBySubcategories){
            countProductsBySubcategories.put((Integer)item.get("subcategory_id"), (Long)item.get("count"));
        }
        
        mav.addObject("categories", categories);
        mav.addObject("countProductsByCategories", countProductsByCategories);
        mav.addObject("countProductsBySubcategories", countProductsBySubcategories);
        
        return mav;
    }
    
}
