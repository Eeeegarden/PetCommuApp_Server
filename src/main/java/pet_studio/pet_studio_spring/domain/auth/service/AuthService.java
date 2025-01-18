package pet_studio.pet_studio_spring.domain.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pet_studio.pet_studio_spring.domain.auth.exception.AuthException;
import pet_studio.pet_studio_spring.domain.image.entity.Image;
import pet_studio.pet_studio_spring.domain.image.service.ImageService;
import pet_studio.pet_studio_spring.domain.user.dto.SignInDto;
import pet_studio.pet_studio_spring.domain.user.dto.SignUpDto;
import pet_studio.pet_studio_spring.domain.user.entity.User;
import pet_studio.pet_studio_spring.domain.user.exception.UserException;
import pet_studio.pet_studio_spring.domain.user.repositoy.UserRepository;
import pet_studio.pet_studio_spring.global.error.code.ExceptionType;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final ImageService imageService;
    private final PasswordEncoder passwordEncoder;

    // 회원가입
    public void signUp(SignUpDto signupDto) throws AuthException {
        // 아이디 중복 여부 확인
        if(userRepository.existsByEmail(signupDto.email())){
            throw new AuthException(ExceptionType.ALREADY_EXIST_USER);
        }
        // 닉네임 중복 확인
        if (!isNicknameAvailable(signupDto.nickName())) {
            throw new AuthException(ExceptionType.ALREADY_EXIST_NICKNAME);
        }

        // User 엔터티 생성
        User user = User.createUser(signupDto, passwordEncoder);

        Image image = Image.builder()
                .url("/profileImages/ic_account.png")
                .user(user)
                .build();

        userRepository.save(user);
    }

    // 로그인
    public User signIn(SignInDto signInDto) throws AuthException {
        return userRepository.findByEmail(signInDto.email())
                .orElseThrow(() -> new AuthException(ExceptionType.NOT_FOUND_USER));
    }

    // 중복 닉네임 확인 메서드 구현
    public boolean isNicknameAvailable(String newNickname) {
        return !userRepository.existsByNickName(newNickname);
    }
}
