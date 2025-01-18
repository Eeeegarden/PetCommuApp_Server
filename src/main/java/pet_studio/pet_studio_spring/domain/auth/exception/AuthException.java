package pet_studio.pet_studio_spring.domain.auth.exception;

import pet_studio.pet_studio_spring.global.error.code.ExceptionType;
import pet_studio.pet_studio_spring.global.error.exception.BaseException;

public class AuthException extends BaseException {
    public AuthException(ExceptionType exceptionType) {
        super(exceptionType);
    }
}
