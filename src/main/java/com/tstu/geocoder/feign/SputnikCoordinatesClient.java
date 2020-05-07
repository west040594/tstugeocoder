package com.tstu.geocoder.feign;

import com.tstu.geocoder.model.SputnikSearchResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "sputnik-coordinates",  url = "${feign.services.sputnik.coordinates.url}")
public interface SputnikCoordinatesClient {
    @GetMapping("/point")
    ResponseEntity<SputnikSearchResponse> getInfoByCoordinates(@RequestParam("lat") String latitude,
                                                               @RequestParam("lon") String longitude,
                                                               @RequestParam("houses") boolean houses);
}
