package karthikpt.profile.Service.client;

import karthikpt.profile.DTO.VehicleDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class VehiclesFallback implements VehiclesFeignClient{
    @Override
    public ResponseEntity<VehicleDTO> getVehicle(String userName) {
        return null;
    }
}
