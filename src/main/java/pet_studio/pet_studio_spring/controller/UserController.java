package pet_studio.pet_studio_spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pet_studio.pet_studio_spring.domain.User;
import pet_studio.pet_studio_spring.dto.image.ImageResponseDto;
import pet_studio.pet_studio_spring.dto.user.NicknameUpdateDto;
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

    // 닉네임 업데이트 API 엔드포인트
    @PutMapping("/updateNickname")
    public ResponseEntity<?> updateNickname(@RequestParam String userId, @RequestParam String newNickname) {
        boolean isUpdated = userService.updateNickname(userId, newNickname);
        if (isUpdated) {
            return ResponseEntity.ok("닉네임이 성공적으로 변경되었습니다.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("사용자를 찾을 수 없습니다.");
        }
    }

    // 닉네임 중복 방지
    @GetMapping("/checkNickname")
    public ResponseEntity<Boolean> checkNickname(@RequestParam String nickname) {
        boolean isAvailable = userService.isNicknameAvailable(nickname);
        return ResponseEntity.ok(isAvailable);
    }

    // 한줄소개 업데이트 API 엔드포인트
    @PutMapping("/updateIntroduce")
    public ResponseEntity<?> updateIntroduce(@RequestParam String userId, @RequestParam String newIntroduce) {
        boolean isUpdated = userService.updateIntroduce(userId, newIntroduce);
        if (isUpdated) {
            return ResponseEntity.ok("한줄소개가 성공적으로 변경되었습니다.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("사용자를 찾을 수 없습니다.");
        }
    }

    // 본인 팔로우/팔로잉 목록 조회
    @GetMapping("/follow")
    public ResponseEntity<UserFollowListDto> getFollowList(String userId) {

        return ResponseEntity.ok(userService.getFollowList(userId));

    }
}