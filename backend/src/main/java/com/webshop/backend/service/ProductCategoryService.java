package com.webshop.backend.service;

import com.webshop.backend.dto.ProductCategoryDTO;
import com.webshop.backend.dto.ProductCategorySubcategoryDTO;
import com.webshop.backend.dto.SubcategoryDTO;
import com.webshop.backend.model.ProductCategory;
import com.webshop.backend.repository.ProductCategoryRepository;

import java.util.ArrayList;
import java.util.List;

import com.webshop.backend.repository.SubcategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductCategoryService {
  @Autowired
  private ProductCategoryRepository productCategoryRepository;

  @Autowired
  private SubcategoryRepository subcategoryRepository;

  public List<ProductCategoryDTO> getAllProductCategory(){
    return productCategoryRepository.findAllProductCategories();
  }

  public List<ProductCategorySubcategoryDTO> getAllProductCategoryWithSubcategories(){
    List<ProductCategoryDTO> productCategories = productCategoryRepository.findAllProductCategories();
    List<ProductCategorySubcategoryDTO> subcategoryDTOList = new ArrayList<>();

    productCategories.forEach(productCategory -> {
      ProductCategorySubcategoryDTO productCategorySubcategoryDTO = new ProductCategorySubcategoryDTO();

      productCategorySubcategoryDTO.setCategoryId(productCategory.getId());
      productCategorySubcategoryDTO.setCategoryName(productCategory.getProductCategoryName());


      productCategorySubcategoryDTO.setSubcategoryList(
              ProductSubcategoryService.convertSubcategoryListToSubcategoryDTOList(
                      subcategoryRepository.findByProductCategoryId(productCategory.getId())));

      subcategoryDTOList.add(productCategorySubcategoryDTO);  
    });

    return subcategoryDTOList;
  }
}
