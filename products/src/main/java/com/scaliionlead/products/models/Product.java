package com.scaliionlead.products.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private String id;
    private String name;
    private String providerId;
    private Provider provider;

    public Product(String id, String name, String providerId) {
        this.id = id;
        this.name = name;
        this.providerId = providerId;
    }

}