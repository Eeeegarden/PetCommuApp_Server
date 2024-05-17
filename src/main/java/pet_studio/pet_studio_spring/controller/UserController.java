package pet_studio.pet_studio_spring.controller;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pet_studio.pet_studio_spring.data.dto.UserDTO;
import pet_studio.pet_studio_spring.data.dto.UserDao;
import pet_studio.pet_studio_spring.data.dto.mypage.UserProfileDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private UserDao userDao;
	//private Map<String, UserDTO> userMap;
    @GetMapping("/user/get-all")
    public List<UserDTO> getAll() {
        return userDao.getAllUsers();
    }
    @PostMapping("/user/save")
    public UserDTO save(@RequestBody UserDTO user){
        return userDao.save(user);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<UserProfileDTO> myPageMain(@PathVariable("userId") String userId) {
        ResponseEntity<UserProfileDTO> response = userDao.myPageMain(userId);
        if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return response;
    }


    @PostMapping("/user/login")
    public UserDTO login(@RequestBody UserDTO user){
        String userId = user.getUserId();
        String userPassword = user.getUserPassword();

        return userDao.login(userId, userPassword);
    }

}

