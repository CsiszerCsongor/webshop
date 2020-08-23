package com.webshop.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Country extends BaseEntity {
    private String name;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "country")
    private List<Country> countryList;
}
