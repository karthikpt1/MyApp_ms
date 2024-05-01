package karthikpt.vehicles.controller;


import karthikpt.vehicles.dto.ResponseDTO;
import karthikpt.vehicles.dto.VehicleDTO;
import karthikpt.vehicles.service.IVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/myApp", produces = {MediaType.APPLICATION_JSON_VALUE})
public class VehicleController {

    private final IVehicleService iVehicleService;

    @Value("${custom.env.name}")
    private String env;
    @Autowired
    public VehicleController(IVehicleService iVehicleService) {
        this.iVehicleService = iVehicleService;
    }


    @GetMapping(path="/vehicle")
    public ResponseEntity<VehicleDTO> getVehicle(@RequestParam String userName){
        VehicleDTO vehicleDto = iVehicleService.getVehicle(userName);
        return ResponseEntity
                .status(HttpStatus.OK)
                .header("environment", env)
                .body(vehicleDto);
    }

    @PostMapping(path="/vehicle")
    public ResponseEntity<ResponseDTO> createVehicle(@RequestBody VehicleDTO vehicleDto) {
        iVehicleService.createVehicle(vehicleDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO("success", "vehicle created successfully"));


    }
}
