package com.webshop.backend.controller;

import com.webshop.backend.dto.ProductCategoryDTO;
import com.webshop.backend.dto.ProductCategorySubcategoryDTO;
import com.webshop.backend.model.ProductCategory;
import com.webshop.backend.service.ProductCategoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/productCategory")
@CrossOrigin(origins = "*")//"http://localhost:4200")
public class ProductCategoryController {

  @Autowired
  private ProductCategoryService productCategoryService;

  @GetMapping("/categories")
  public List<ProductCategorySubcategoryDTO> getCategories(){
    return productCategoryService.getAllProductCategoryWithSubcategories();
  }

}
