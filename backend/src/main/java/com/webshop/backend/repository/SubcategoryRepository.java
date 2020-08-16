package com.webshop.backend.repository;

import com.webshop.backend.model.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubcategoryRepository extends JpaRepository<Subcategory, Long> {
    @Query("SELECT sc " +
            "FROM Subcategory sc " +
            "WHERE sc.productCategory.id = :productCategoryId")
    List<Subcategory> findByProductCategoryId(@Param("productCategoryId") Long productCategoryId);

}
