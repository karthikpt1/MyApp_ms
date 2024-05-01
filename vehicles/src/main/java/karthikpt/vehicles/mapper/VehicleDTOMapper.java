package karthikpt.vehicles.mapper;

import karthikpt.vehicles.dto.VehicleDTO;
import karthikpt.vehicles.entity.Vehicle;

public class VehicleDTOMapper {


    public static VehicleDTO mapToVehicleDto(Vehicle vehicle, VehicleDTO vehicleDto){
        vehicleDto.setEmirate(vehicle.getEmirate());
        vehicleDto.setPlateCode(vehicle.getPlateCode());
        vehicleDto.setUserName(vehicle.getUserName());
        vehicleDto.setPlateNo(vehicle.getPlateNo());
        vehicleDto.setPlateCategory(vehicle.getPlateCategory());
        return vehicleDto;
    }

    public static Vehicle mapToVehicle(VehicleDTO vehicleDto, Vehicle vehicle){
        vehicle.setEmirate(vehicleDto.getEmirate());
        vehicle.setPlateNo(vehicleDto.getPlateNo());
        vehicle.setPlateCode(vehicleDto.getPlateCode());
        vehicle.setUserName(vehicleDto.getUserName());
        vehicle.setPlateCategory(vehicleDto.getPlateCategory());
        return vehicle;
    }

}
