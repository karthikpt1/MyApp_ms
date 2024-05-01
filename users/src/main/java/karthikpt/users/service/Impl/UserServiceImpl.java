package karthikpt.users.service.Impl;

import karthikpt.users.dto.UserDTO;
import karthikpt.users.entity.UserProfile;
import karthikpt.users.exception.NotFoundException;
import karthikpt.users.exception.UserExistsException;
import karthikpt.users.mapper.UserDTOMapper;
import karthikpt.users.repository.UserProfileRepository;
import karthikpt.users.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {



    private final UserProfileRepository userProfileRepository;

    @Autowired
    public UserServiceImpl(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }
    @Override
    public UserDTO getUserProfile(String userName) {
        UserProfile userDetails = userProfileRepository.findByUserName(userName).orElseThrow(
                () -> new NotFoundException("User Not Found"));
        return UserDTOMapper.mapToUserDto(userDetails, new UserDTO());

    }

    @Override
    public void createUserProfile(UserDTO userDto){
        UserProfile userProfile = UserDTOMapper.mapToUserProfile(userDto, new UserProfile());
        Optional<UserProfile> existingUser = userProfileRepository.findByUserName(userProfile.getUserName());
        if(existingUser.isPresent())
            throw new UserExistsException(userProfile.getUserName());
        UserProfile savedUserProfile = userProfileRepository.save(userProfile);
    }
}
