package karthikpt.vehicles.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class VehicleExistsException extends RuntimeException{

    public VehicleExistsException(String userName){
        super(String.format("Vehicle already exists for user %s", userName));
    }

}
