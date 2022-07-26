package com.dio.cloudparking.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dio.cloudparking.model.Parking;

@RestController
@RequestMapping("/parking")
public class ParkingController {
    
    @GetMapping
    public List<Parking> findAll() {
        var parking = new Parking();
        parking.setColor("preto");
        parking.setLicense("MDR-4532");
        parking.setModel("VW Fusca");
        parking.setState("MS");

        return Arrays.asList(parking, parking);
    }
}
