package karthikpt.profile.Controller;

import karthikpt.profile.DTO.ProfileDTO;
import karthikpt.profile.Service.IProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/myApp", produces = {MediaType.APPLICATION_JSON_VALUE})
public class ProfileController {

    private final IProfileService iProfileService;
    @Value("${custom.env.name}")
    private String env;
    @Autowired
    public ProfileController(IProfileService iProfileService) {
        this.iProfileService = iProfileService;
    }



@GetMapping(path="/profile")
    public ResponseEntity<ProfileDTO> getProfile(@RequestParam String userName){
        ProfileDTO profileDTO = iProfileService.fetchProfileDetails(userName);
       return ResponseEntity
               .status(HttpStatus.OK)
               .header("environment", env)
               .body(profileDTO);

}
}
