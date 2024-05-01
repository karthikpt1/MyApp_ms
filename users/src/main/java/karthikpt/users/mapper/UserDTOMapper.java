package karthikpt.users.mapper;

import karthikpt.users.entity.UserProfile;
import karthikpt.users.dto.UserDTO;

public class UserDTOMapper {


    public static UserDTO mapToUserDto(UserProfile userProfile, UserDTO userDTO){
        userDTO.setDateOfBirth(userProfile.getDateOfBirth());
        userDTO.setEmail(userProfile.getEmail());
        userDTO.setUserName(userProfile.getUserName());
        userDTO.setMobile(userProfile.getMobile());
        userDTO.setLastName(userProfile.getLastName());
        userDTO.setFirstName(userProfile.getFirstName());
        return userDTO;
    }

    public static UserProfile mapToUserProfile(UserDTO userDto, UserProfile userProfile){
        userProfile.setDateOfBirth(userDto.getDateOfBirth());
        userProfile.setUserName(userDto.getUserName());
        userProfile.setEmail(userDto.getEmail());
        userProfile.setMobile(userDto.getMobile());
        userProfile.setFirstName(userDto.getFirstName());
        userProfile.setLastName(userDto.getLastName());
        return userProfile;
    }

}
