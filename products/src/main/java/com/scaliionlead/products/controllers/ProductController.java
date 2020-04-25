package com.scaliionlead.products.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.scaliionlead.products.models.Product;
import com.scaliionlead.products.models.Provider;
import com.scaliionlead.products.proxies.ProviderProxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@EnableFeignClients(basePackages = "com.scaliionlead.products.proxies")
public class ProductController {

    @Autowired
    private ProviderProxy providerProxy;

    private List<Product> products = Stream
            .of(new Product("1", "product1", "1"), new Product("2", "product2", "2"), new Product("3", "product3", "1"))
            .collect(Collectors.toList());

    @GetMapping
    public List<Product> getAll() {
        return this.products;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getDetail(@PathVariable String id) {

        Optional<Product> product$ = this.products.stream().filter(p -> id.equals(p.getId())).findFirst();

        if (!product$.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Product product = product$.get();
        Provider provider = providerProxy.getDetails(product.getProviderId());
        product.setProvider(provider);
        return new ResponseEntity<Product>(product, HttpStatus.ACCEPTED);
    }
}