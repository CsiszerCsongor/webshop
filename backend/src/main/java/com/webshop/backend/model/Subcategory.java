package com.webshop.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCT_SUBCATEGORIES")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Subcategory extends BaseEntity{
    private String name;
    @ManyToOne
    @JoinColumn(name = "productCategory_id")
    private ProductCategory productCategory;
}
