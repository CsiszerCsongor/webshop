package com.webshop.backend.service;

import com.webshop.backend.dto.ProductCategoryDTO;
import com.webshop.backend.model.ProductCategory;
import com.webshop.backend.repository.ProductCategoryRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductCategoryService {
  @Autowired
  private ProductCategoryRepository productCategoryRepository;

  public List<ProductCategoryDTO> getAllProductCategory(){
    return productCategoryRepository.findAllProductCategories();
  }
}
