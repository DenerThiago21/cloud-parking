package com.dio.cloudparking.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.dio.cloudparking.exception.ParkingNotFoundException;
import com.dio.cloudparking.model.Parking;
import com.dio.cloudparking.repository.ParkingRepository;

@Service
public class ParkingService {

    private final ParkingRepository parkingRepository;

    public ParkingService(ParkingRepository parkingRepository) {
        this.parkingRepository = parkingRepository;
    }

    public List<Parking> findAll() {
        return parkingRepository.findAll();
    }

    public Parking findById(String id) {
        return parkingRepository.findById(id).orElseThrow(() -> 
                new ParkingNotFoundException(id));
    }

    public Parking create(Parking parkingCreate) {
        String uuid = getUUID();
        parkingCreate.setId(uuid);
        parkingCreate.setEntryDate(LocalDateTime.now());
        parkingRepository.save(parkingCreate);
        return parkingCreate;
    }

    public void delete(String id) {
        findById(id);
        parkingRepository.deleteById(id);
    }
    
    public Parking update(String id, Parking parkingCreate) {
        Parking parking = findById(id);
        parking.setColor(parkingCreate.getColor());
        parking.setLicense(parkingCreate.getLicense());
        parking.setModel(parkingCreate.getModel());
        parking.setState(parkingCreate.getState());
        parkingRepository.save(parking);
        return parking;
    }

    public Parking exit(String id) {
        //pegar o objeto
        //setar a hora de saida do estacionamento
        //calcular o valor total
        return null;
    }

    private static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    

}
