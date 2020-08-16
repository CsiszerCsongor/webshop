package com.webshop.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategorySubcategoryDTO {
    private Long categoryId;
    private String categoryName;
    private List<SubcategoryDTO> subcategoryList;
}
