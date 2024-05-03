package karthikpt.profile.Service.client;

import karthikpt.profile.DTO.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class UsersFallback implements UsersFeignClient{
    @Override
    public ResponseEntity<UserDTO> getUserProfile(String userName) {
        return null;
    }
}
