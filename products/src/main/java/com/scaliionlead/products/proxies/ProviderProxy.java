package com.scaliionlead.products.proxies;

import com.scaliionlead.products.models.Provider;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * ProviderProxy
 */

@FeignClient(name = "providers-service", url = "${microservice.providers.url}")
public interface ProviderProxy {

    @GetMapping(value = "/providers/{id}")
    public Provider getDetails(@PathVariable("id") String id);

}