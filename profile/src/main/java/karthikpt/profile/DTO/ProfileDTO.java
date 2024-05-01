package karthikpt.profile.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ProfileDTO {

  private UserDTO userDto;
  private VehicleDTO vehicleDto;
}
