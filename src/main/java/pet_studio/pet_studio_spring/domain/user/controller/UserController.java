package pet_studio.pet_studio_spring.domain.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pet_studio.pet_studio_spring.domain.auth.service.AuthService;
import pet_studio.pet_studio_spring.domain.user.entity.User;
import pet_studio.pet_studio_spring.domain.user.dto.UserFollowListDto;
import pet_studio.pet_studio_spring.domain.user.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final AuthService authService;

    @GetMapping("/get-all")
    public List<User> getAll() {
        return userService.getAllUsers();
    }

    // 프로필 조회
    @GetMapping("/{email}")
    public ResponseEntity<?> myPageMain(@PathVariable("email") String email) {
        ResponseEntity<?> response = userService.myPageMain(email);
        if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return response;
    }

    // 닉네임 업데이트 API 엔드포인트
    @PutMapping("/updateNickname")
    public ResponseEntity<?> updateNickname(@RequestParam String email, @RequestParam String newNickname) {
        boolean isUpdated = userService.updateNickname(email, newNickname);
        if (isUpdated) {
            return ResponseEntity.ok("닉네임이 성공적으로 변경되었습니다.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("사용자를 찾을 수 없습니다.");
        }
    }

    // 닉네임 중복 방지
    @GetMapping("/checkNickname")
    public ResponseEntity<Boolean> checkNickname(@RequestParam String nickname) {
        boolean isAvailable = authService.isNicknameAvailable(nickname);
        return ResponseEntity.ok(isAvailable);
    }

    // 한줄소개 업데이트 API 엔드포인트
    @PutMapping("/updateIntroduce")
    public ResponseEntity<?> updateIntroduce(@RequestParam String email, @RequestParam String newIntroduce) {
        boolean isUpdated = userService.updateIntroduce(email, newIntroduce);
        if (isUpdated) {
            return ResponseEntity.ok("한줄소개가 성공적으로 변경되었습니다.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("사용자를 찾을 수 없습니다.");
        }
    }

    // 본인 팔로우/팔로잉 목록 조회
    @GetMapping("/follow")
    public ResponseEntity<UserFollowListDto> getFollowList(String email) {

        return ResponseEntity.ok(userService.getFollowList(email));

    }
}