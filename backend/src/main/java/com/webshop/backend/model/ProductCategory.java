package com.webshop.backend.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PRODUCT_CATEGORIES")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategory extends BaseEntity{
  private String productCategoryName;
}
