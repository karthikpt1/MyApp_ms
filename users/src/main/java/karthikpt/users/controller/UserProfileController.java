package karthikpt.users.controller;

import karthikpt.users.dto.ResponseDTO;
import karthikpt.users.dto.UserDTO;
import karthikpt.users.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/myApp", produces = {MediaType.APPLICATION_JSON_VALUE})
public class UserProfileController {

    private final IUserService iUserService;

    @Value("${custom.env.name}")
    private String env;

    @Autowired
    public UserProfileController(IUserService iUserService) {
        this.iUserService = iUserService;
    }

    @GetMapping(path="/user")
    public ResponseEntity<UserDTO> getUserProfile(@RequestParam String userName){
        UserDTO userDto = iUserService.getUserProfile(userName);
        return ResponseEntity
                .status(HttpStatus.OK)
                .header("environment", env)
                .body(userDto);
    }

    @PostMapping(path="/user")
    public ResponseEntity<ResponseDTO> createUserProfile(@RequestBody UserDTO userDto) {
        iUserService.createUserProfile(userDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("environment", env)
                .body(new ResponseDTO("success", "user created successfully")
                );
    }

}
