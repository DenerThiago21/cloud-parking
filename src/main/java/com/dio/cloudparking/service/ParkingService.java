package com.dio.cloudparking.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.dio.cloudparking.exception.ParkingNotFoundException;
import com.dio.cloudparking.model.Parking;

@Service
public class ParkingService {
    
    private static Map<String, Parking> parkingMap = new HashMap<>();

    static {
        var id = getUUID();
        var id1 = getUUID();
        Parking parking = new Parking(id, "DMS-1111", "MS", "Fusca", "Azul");
        Parking parking1 = new Parking(id1, "MAK-2354", "SC", "Corolla", "Branco");
        parkingMap.put(id, parking);
        parkingMap.put(id1, parking1);
    }

    public List<Parking> findAll() {
        return parkingMap.values().stream().collect(Collectors.toList());
    }

    public Parking findById(String id) {
        Parking parking = parkingMap.get(id);
        if(parking == null) {
            throw new ParkingNotFoundException(id);
        }
        return parking;
    }

    public Parking create(Parking parkingCreate) {
        String uuid = getUUID();
        parkingCreate.setId(uuid);
        parkingCreate.setEntryDate(LocalDateTime.now());
        parkingMap.put(uuid, parkingCreate);
        return parkingCreate;
    }

    public void delete(String id) {
        findById(id);
        parkingMap.remove(id);
    }
    
    public Parking update(String id, Parking parkingCreate) {
        Parking parkingById = findById(id);
        parkingById.setColor(parkingCreate.getColor());
        parkingMap.replace(id, parkingById);
        return parkingById;
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
