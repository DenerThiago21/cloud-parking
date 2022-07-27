package com.dio.cloudparking.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dio.cloudparking.controller.dto.ParkingCreateDTO;
import com.dio.cloudparking.controller.dto.ParkingDTO;
import com.dio.cloudparking.controller.mapper.parkingMapper;
import com.dio.cloudparking.model.Parking;
import com.dio.cloudparking.service.ParkingService;

@RestController
@RequestMapping("/parking")
public class ParkingController {

    private final ParkingService parkingService;
    private final parkingMapper parkingMapper;

    // injeção de dependencia via construtor - há também a injeção via annotation @Autowired
    private ParkingController(ParkingService parkingService, parkingMapper parkingMapper) {
        this.parkingService = parkingService;
        this.parkingMapper = parkingMapper;
    }
    
    @GetMapping
    public ResponseEntity<List<ParkingDTO>> findAll() {
        List<Parking> parkingList = parkingService.findAll();
        List<ParkingDTO> result = parkingMapper.toParkingDTOList(parkingList);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingDTO> findById(@PathVariable String id) {
        Parking parking = parkingService.findById(id);
        ParkingDTO result = parkingMapper.toParkingDTO(parking);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<ParkingDTO> create(@RequestBody ParkingCreateDTO dto) {
        var parkingCreate = parkingMapper.toParkingCreate(dto);
        Parking parking = parkingService.create(parkingCreate);
        ParkingDTO result = parkingMapper.toParkingDTO(parking);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
}
