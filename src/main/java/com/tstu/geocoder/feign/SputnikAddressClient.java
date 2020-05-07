package com.tstu.geocoder.feign;

import com.tstu.geocoder.model.SputnikSearchResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "sputnik-address",  url = "${feign.services.sputnik.address.url}")
public interface SputnikAddressClient {
    @GetMapping("/search/addr")
    ResponseEntity<SputnikSearchResponse> getInfoByAddress(@RequestParam("q") String address);
}
