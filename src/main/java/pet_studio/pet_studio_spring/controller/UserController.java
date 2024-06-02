package pet_studio.pet_studio_spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pet_studio.pet_studio_spring.domain.User;
import pet_studio.pet_studio_spring.dto.image.ImageResponseDto;
import pet_studio.pet_studio_spring.dto.user.UserDto;
import pet_studio.pet_studio_spring.dto.user.UserFollowListDto;
import pet_studio.pet_studio_spring.service.UserService;
import pet_studio.pet_studio_spring.service.UserServiceImpl;
import pet_studio.pet_studio_spring.dto.mypage.UserProfileDto;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/get-all")
    public List<User> getAll() {
        return userService.getAllUsers();
    }

    // 회원가입
    @PostMapping("/save")
    public ResponseEntity<?> saveUser(@RequestBody UserDto user){
        return userService.save(user);
    }

    // 프로필 조회
    @GetMapping("/{userId}")
    public ResponseEntity<UserProfileDto> myPageMain(@PathVariable("userId") String userId) {
        ResponseEntity<UserProfileDto> response = userService.myPageMain(userId);
        if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return response;
    }


    // 로그인
    @PostMapping("/login")
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

    // 본인 팔로우/팔로잉 목록 조회
    @GetMapping("/follow")
    public ResponseEntity<UserFollowListDto> getFollowList(String userId) {

        return ResponseEntity.ok(userService.getFollowList(userId));
    }
}

