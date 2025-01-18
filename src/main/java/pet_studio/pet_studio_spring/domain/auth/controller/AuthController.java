package pet_studio.pet_studio_spring.domain.auth.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pet_studio.pet_studio_spring.domain.auth.service.AuthService;
import pet_studio.pet_studio_spring.domain.user.dto.SignInDto;
import pet_studio.pet_studio_spring.domain.user.dto.SignUpDto;
import pet_studio.pet_studio_spring.domain.user.entity.User;
import pet_studio.pet_studio_spring.global.common.response.ResponseDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    // 회원가입
    @PostMapping("/signUp")
    public ResponseEntity<ResponseDto<String>> signUpUser(@Valid @RequestBody SignUpDto user){
        authService.signUp(user);
        return ResponseEntity.ok(ResponseDto.of(200, "회원가입이 완료되었습니다.", "회원가입 성공"));
    }


    // 로그인
    @PostMapping("/signIn")
    public ResponseEntity<ResponseDto<User>> signIn(@Valid @RequestBody SignInDto signInDto) {
        User user = authService.signIn(signInDto);
        return ResponseEntity.ok(ResponseDto.of(200, "로그인이 완료되었습니다.", user));
    }
}
