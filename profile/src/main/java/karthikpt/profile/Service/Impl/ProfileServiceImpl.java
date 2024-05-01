package karthikpt.profile.Service.Impl;

import feign.Response;
import karthikpt.profile.DTO.ProfileDTO;
import karthikpt.profile.DTO.UserDTO;
import karthikpt.profile.DTO.VehicleDTO;
import karthikpt.profile.Service.IProfileService;
import karthikpt.profile.Service.client.UsersFeignClient;
import karthikpt.profile.Service.client.VehiclesFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProfileServiceImpl implements IProfileService {

    private UsersFeignClient usersFeignClient;
    private VehiclesFeignClient vehiclesFeignClient;

    @Override
    public ProfileDTO fetchProfileDetails(String userName) {

        ResponseEntity<UserDTO> userDTOResponseEntity = usersFeignClient.getUserProfile(userName);
        ResponseEntity<VehicleDTO> vehicleDTOResponseEntity = vehiclesFeignClient.getVehicle(userName);

        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setUserDto(userDTOResponseEntity.getBody());
        profileDTO.setVehicleDto(vehicleDTOResponseEntity.getBody());

        return profileDTO;
    }
}
