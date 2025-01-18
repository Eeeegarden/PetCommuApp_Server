package pet_studio.pet_studio_spring.global.error.code;

import org.springframework.http.HttpStatus;

public record ExceptionCode(
        HttpStatus httpStatus,
        String errorMessage
) {
    public static ExceptionCode from(ExceptionType ex) {
        return new ExceptionCode(ex.getHttpStatus(), ex.getErrorMessage());
    }
}
