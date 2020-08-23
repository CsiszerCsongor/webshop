package com.webshop.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.List;

@Data
@Entity(name = "STREETS")
@NoArgsConstructor
@AllArgsConstructor
public class Street extends BaseEntity{
    private String name;
    @ManyToOne
    @JoinColumn(name = "place_id")
    private Place place;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "street")
    private List<Address> addressList;
}
