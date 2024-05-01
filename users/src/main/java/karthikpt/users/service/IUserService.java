package karthikpt.users.service;

import karthikpt.users.dto.UserDTO;

public interface IUserService {

    UserDTO getUserProfile (String userName);

    void createUserProfile(UserDTO userDto);
}
