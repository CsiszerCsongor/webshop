package com.webshop.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity(name = "ADDRESSES")
@NoArgsConstructor
@AllArgsConstructor
public class Address extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "street_id")
    private Street street;

    private String address;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "address")
    private List<User> userList;
}
