package pet_studio.pet_studio_spring.domain.auth.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import pet_studio.pet_studio_spring.domain.auth.exception.AuthException;
import pet_studio.pet_studio_spring.domain.auth.service.AuthService;
import pet_studio.pet_studio_spring.domain.user.dto.SignUpDto;
import pet_studio.pet_studio_spring.global.error.code.ExceptionType;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class AuthControllerTest {

    @Autowired
    private AuthService authService;

}