package pet_studio.pet_studio_spring.domain.user.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import pet_studio.pet_studio_spring.domain.auth.service.AuthService;
import pet_studio.pet_studio_spring.domain.user.dto.SignUpDto;
import pet_studio.pet_studio_spring.domain.user.exception.UserException;
import pet_studio.pet_studio_spring.domain.user.repositoy.UserRepository;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class UserServiceTest {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthService authService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private SignUpDto testUser1;
    private SignUpDto testUser3;

    @BeforeEach
    void setUp() {
        testUser1 = new SignUpDto("testuser", "Password123!", "TestNick");
    }


    @Test
    @DisplayName("이름 중복일때 에러발생")
    void saveTestWithDuplicateUser() {
        SignUpDto testUser = new SignUpDto("testuser", "Password123!", "TestNick");
        authService.signUp(testUser);

        SignUpDto duplicateUser = new SignUpDto("testuser", "Password456!", "AnotherNick");
        assertThrows(UserException.class, () -> authService.signUp(duplicateUser));
    }

    @Test
    void getAllUsers() {
    }

    @Test
    void myPageMain() {
    }

    @Test
    void updateNickname() {
    }

    @Test
    void updateIntroduce() {
    }

    @Test
    void getFollowList() {
    }

    @Test
    void isNicknameAvailable() {
    }
}