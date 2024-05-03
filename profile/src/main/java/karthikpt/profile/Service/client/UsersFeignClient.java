package karthikpt.profile.Service.client;

import karthikpt.profile.DTO.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "users", fallback = UsersFallback.class)
public interface UsersFeignClient {
    @GetMapping(path="/myApp/user", consumes = "application/json")
    public ResponseEntity<UserDTO> getUserProfile(@RequestParam String userName);

}
