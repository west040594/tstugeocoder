package com.tstu.geocoder.service;

import com.tstu.geocoder.feign.SputnikAddressClient;
import com.tstu.geocoder.feign.SputnikCoordinatesClient;
import com.tstu.geocoder.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class SputnikService {

    private final SputnikAddressClient addressClient;
    private final SputnikCoordinatesClient coordinatesClient;

    public Coordinates getCoordinatesByAddress(String address) {
        Optional<Coordinates> coordinates = Optional.ofNullable(addressClient.getInfoByAddress(address).getBody())
                .map(SputnikSearchResponse::getResult)
                .map(Result::getAddress)
                .flatMap(addresses -> addresses.stream().findFirst())
                .map(Address::getFeatures)
                .flatMap(features -> features.stream().findFirst())
                .map(Feature::getGeometry)
                .map(GeometryWrapper::getGeometries)
                .flatMap(geometries -> geometries.stream().findFirst())
                .map(Geometry::getCoordinates)
                .map(coordinatesList -> new Coordinates(coordinatesList.get(1), coordinatesList.get(0)));

        if(coordinates.isPresent()) {
            log.info("Координаты найдены - {}", coordinates);
            return coordinates.get();
        } else {
            throw new RuntimeException("Координаты не найдены");
        }
    }

    public String getAddressByCoordinates(Coordinates coordinates) {
        Optional<String> address = Optional.ofNullable(
                coordinatesClient.getInfoByCoordinates(coordinates.getLatitude(), coordinates.getLongitude(), Boolean.TRUE).getBody())
                .map(SputnikSearchResponse::getResult)
                .map(Result::getAddress)
                .flatMap(addresses -> addresses.stream().findFirst())
                .map(Address::getFeatures)
                .flatMap(features -> features.stream().findFirst())
                .map(Feature::getProperties)
                .map(Properties::getDisplayName);

        if(address.isPresent()) {
            log.info("Адрес найден - {}", address);
            return address.get();
        } else {
            throw new RuntimeException("Адрес не найден");
        }

    }
}
