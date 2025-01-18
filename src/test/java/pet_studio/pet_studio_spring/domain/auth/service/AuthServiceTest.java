package pet_studio.pet_studio_spring.domain.auth.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import pet_studio.pet_studio_spring.domain.auth.exception.AuthException;
import pet_studio.pet_studio_spring.domain.user.dto.SignUpDto;
import pet_studio.pet_studio_spring.domain.user.entity.User;
import pet_studio.pet_studio_spring.domain.user.repositoy.UserRepository;
import pet_studio.pet_studio_spring.global.error.code.ExceptionType;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
@Transactional
class AuthServiceTest {

    @Autowired
    private AuthService authService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        SignUpDto signUpDto = new SignUpDto("wjddnjs1111","Qwer1234!","Trump");
        User user1 = User.createUser(signUpDto,passwordEncoder);

        userRepository.save(user1);
    }

    @Test
    @DisplayName("정상 회원가입")
    void signUpTest() {

        SignUpDto testUser1 = new SignUpDto("wjddnjs3333", "Qwer1234!", "Garden");

        authService.signUp(testUser1);

        assertThat(userRepository.existsByEmail(testUser1.email())).isTrue();
    }

    @Test
    @DisplayName("아이디 중복")
    void duplicateEmailTest() throws AuthException {
        SignUpDto testUser1 = new SignUpDto("wjddnjs1111", "Qwer1234!", "Garden");

        AuthException authException = assertThrows(AuthException.class,
                () -> authService.signUp(testUser1));

        assertThat(authException.getExceptionType()).isEqualTo(ExceptionType.ALREADY_EXIST_USER);
    }

    @Test
    @DisplayName("닉네임 중복")
    void duplicateNickNameTest() throws AuthException {
        SignUpDto testUser1 = new SignUpDto("wjddnjs2222", "Qwer1234!", "Trump");

        AuthException authException = assertThrows(AuthException.class,
                () -> authService.signUp(testUser1));

        assertThat(authException.getExceptionType()).isEqualTo(ExceptionType.ALREADY_EXIST_NICKNAME);
    }



}
