package karthikpt.vehicles.service;


import karthikpt.vehicles.dto.VehicleDTO;

public interface IVehicleService {

    VehicleDTO getVehicle (String userName);

    void createVehicle(VehicleDTO vehicleDTO);
}
