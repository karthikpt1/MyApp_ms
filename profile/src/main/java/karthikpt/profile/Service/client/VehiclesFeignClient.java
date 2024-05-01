package karthikpt.profile.Service.client;

import karthikpt.profile.DTO.VehicleDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("vehicles")
public interface VehiclesFeignClient {

    @GetMapping(path="/myApp/vehicle", consumes = "application/json")
    public ResponseEntity<VehicleDTO> getVehicle(@RequestParam String userName);
}
