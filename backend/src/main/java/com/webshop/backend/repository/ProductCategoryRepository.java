package com.webshop.backend.repository;

import com.webshop.backend.dto.ProductCategoryDTO;
import com.webshop.backend.dto.ProductCategorySubcategoryDTO;
import com.webshop.backend.model.ProductCategory;
import java.util.List;

import com.webshop.backend.model.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
  @Query("SELECT new com.webshop.backend.dto.ProductCategoryDTO(pc.id, pc.productCategoryName) FROM ProductCategory pc")
  List<ProductCategoryDTO> findAllProductCategories();

  /*@Query("SELECT new com.webshop.backend.dto.ProductCategorySubcategoryDTO(pc.id, pc.productCategoryName) " +
          "FROM ProductCategory pc " +
          "LEFT JOIN pc.subcategoryList subcategory " +
          "ORDER BY subcategory.id")
  List<ProductCategorySubcategoryDTO> findAllProductCategoriesWithSubcategories();*/
}
