package karthikpt.vehicles.service.Impl;


import karthikpt.vehicles.dto.VehicleDTO;
import karthikpt.vehicles.entity.Vehicle;
import karthikpt.vehicles.exception.NotFoundException;
import karthikpt.vehicles.exception.VehicleExistsException;
import karthikpt.vehicles.mapper.VehicleDTOMapper;
import karthikpt.vehicles.repository.VehicleRepository;
import karthikpt.vehicles.service.IVehicleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class VehicleServiceImpl implements IVehicleService {



    private final VehicleRepository vehicleRepository;

    @Autowired
    public VehicleServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }


    @Override
    public VehicleDTO getVehicle(String userName) {
        log.info("Request received in the get Vehicle method of vehicle service for "+ userName);
        Vehicle vehicle = vehicleRepository.findByUserName(userName).orElseThrow(
                () -> new NotFoundException("Vehicle Not Found"));

        return VehicleDTOMapper.mapToVehicleDto(vehicle, new VehicleDTO());
    }

    @Override
    public void createVehicle(VehicleDTO vehicleDto) {
        log.info("Request received in the create vehicle method of vehicle service for "+ vehicleDto.getUserName());
        Vehicle vehicle = VehicleDTOMapper.mapToVehicle(vehicleDto, new Vehicle());
        Optional<Vehicle> existingVehicle = vehicleRepository.findByUserName(vehicle.getUserName());
        if(existingVehicle.isPresent())
            throw new VehicleExistsException(vehicle.getUserName());
        Vehicle savedVehicle = vehicleRepository.save(vehicle);

    }
}
