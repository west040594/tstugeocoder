package com.tstu.geocoder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class GeocoderApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeocoderApplication.class, args);
	}
}
