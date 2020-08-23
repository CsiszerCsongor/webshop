package com.webshop.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Entity(name="USERS")
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String phoneNumber;
    private String email;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;
}
