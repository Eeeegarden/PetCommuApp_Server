package pet_studio.pet_studio_spring.domain.user.exception;


import pet_studio.pet_studio_spring.global.error.code.ExceptionType;
import pet_studio.pet_studio_spring.global.error.exception.BaseException;

public class UserException extends BaseException {

    public UserException(ExceptionType exceptionType) {
        super(exceptionType);
    }

}