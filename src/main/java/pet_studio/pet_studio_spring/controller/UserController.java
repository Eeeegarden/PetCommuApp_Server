package pet_studio.pet_studio_spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pet_studio.pet_studio_spring.domain.User;
import pet_studio.pet_studio_spring.dto.user.UserDto;
import pet_studio.pet_studio_spring.service.UserService;
import pet_studio.pet_studio_spring.service.UserServiceImpl;
import pet_studio.pet_studio_spring.dto.mypage.UserProfileDto;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
	//private Map<String, UserDTO> userMap;
    @GetMapping("/user/get-all")
    public List<User> getAll() {
        return userService.getAllUsers();
    }
    @PostMapping("/user/save")
    public ResponseEntity<?> saveUser(@RequestBody UserDto user){
        return userService.save(user);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<UserProfileDto> myPageMain(@PathVariable("userId") String userId) {
        ResponseEntity<UserProfileDto> response = userService.myPageMain(userId);
        if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return response;
    }


    @PostMapping("/user/login")
    public ResponseEntity<User> login(@RequestBody UserDto user) {

        String userId = user.getUserId();
        String userPassword = user.getUserPassword();

        User loggedInUser = userService.login(userId, userPassword);

        // 만약 로그인이 성공했다면 해당 사용자 정보를 반환합니다.
        if (loggedInUser != null) {
            return ResponseEntity.ok(loggedInUser);
        } else {
            // 로그인에 실패했을 경우 401 Unauthorized 상태를 반환합니다.
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}

