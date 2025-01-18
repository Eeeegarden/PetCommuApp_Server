package pet_studio.pet_studio_spring.global.error.exception;

import lombok.Getter;
import pet_studio.pet_studio_spring.global.error.code.ExceptionType;

@Getter
public class BaseException extends RuntimeException{
    private final ExceptionType exceptionType;

    protected BaseException(ExceptionType exceptionType) {
        super(exceptionType.getErrorMessage());
        this.exceptionType = exceptionType;
    }
}