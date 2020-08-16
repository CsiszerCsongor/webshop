package com.webshop.backend.model;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "PRODUCT_CATEGORIES")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategory extends BaseEntity{
  private String productCategoryName;
  @OneToMany(cascade = CascadeType.ALL,mappedBy = "productCategory")
  private List<Subcategory> subcategoryList;
}
