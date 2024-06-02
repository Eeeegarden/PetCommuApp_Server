package pet_studio.pet_studio_spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import pet_studio.pet_studio_spring.domain.User;
import pet_studio.pet_studio_spring.dto.mypage.UserProfileDto;
import pet_studio.pet_studio_spring.dto.user.UserDto;
import pet_studio.pet_studio_spring.dto.user.UserFollowListDto;
import pet_studio.pet_studio_spring.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;


public interface UserService {

    public User findUserById(String userid);
    public ResponseEntity<?> save(UserDto user);

    public List<User> getAllUsers();

    public User login(String userId, String userPassword);

    public ResponseEntity myPageMain(@PathVariable("userId") String userId);
    public UserFollowListDto getFollowList(String userId);


}