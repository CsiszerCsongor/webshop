package com.webshop.backend.service;

import com.webshop.backend.dto.ProductCategorySubcategoryDTO;
import com.webshop.backend.dto.SubcategoryDTO;
import com.webshop.backend.model.ProductCategory;
import com.webshop.backend.model.Subcategory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductSubcategoryService {
    public static SubcategoryDTO convertSubcategoryToSubcategoryDTO(Subcategory subcategory){
        SubcategoryDTO subcategoryDTO = new SubcategoryDTO();
        subcategoryDTO.setId(subcategory.getId());
        subcategoryDTO.setName(subcategory.getName());
        return subcategoryDTO;
    }

    public static List<SubcategoryDTO> convertSubcategoryListToSubcategoryDTOList(List<Subcategory> subcategoryList){
        List<SubcategoryDTO> subcategoryDTOList = new ArrayList<>();
        subcategoryList.forEach(subcategory -> {
            subcategoryDTOList.add(convertSubcategoryToSubcategoryDTO(subcategory));
        });

        return subcategoryDTOList;
    }
}
