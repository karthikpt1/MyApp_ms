package karthikpt.profile.Service;

import karthikpt.profile.DTO.ProfileDTO;
import karthikpt.profile.Service.client.UsersFeignClient;

public interface IProfileService {

    ProfileDTO fetchProfileDetails(String userName);

}
