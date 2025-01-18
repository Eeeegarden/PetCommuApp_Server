package pet_studio.pet_studio_spring.global.error.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ExceptionType {

    /*
    이메일/비밀번호 형식 오류는 HttpStatus.BAD_REQUEST (400).
            **이미 존재하는 리소스(이메일/전화번호)**는 HttpStatus.CONFLICT (409).
    잘못된 비밀번호는 HttpStatus.UNAUTHORIZED (401).
    사용자 정보를 찾을 수 없는 경우는 HttpStatus.NOT_FOUND (404).
    */

    // 권한이 없는 유저
    FORBIDDEN_USER(HttpStatus.FORBIDDEN, "권한이 없습니다."), // 403 Forbidden

    ALREADY_EXIST_USER(HttpStatus.CONFLICT, "이미 존재하는 아이디입니다."), // 409 Conflict
    ALREADY_EXIST_NICKNAME(HttpStatus.CONFLICT, "이미 존재하는 닉네임입니다."), // 409 Conflict
    ALREADY_EXIST_PHONENUMBER(HttpStatus.CONFLICT, "이미 가입된 번호입니다."), // 409 Conflict
    WRONG_PASSWORD(HttpStatus.UNAUTHORIZED, "비밀번호가 잘못되었습니다."), // 401 Unauthorized
    NOT_FOUND_USER(HttpStatus.NOT_FOUND, "회원 정보가 없습니다."), // 404 Not Found
    SIGNUP_FORMAT_INVALID(HttpStatus.BAD_REQUEST, "유효성 에러."), // 400 Bad Request
    EMAIL_FORMAT_INVALID(HttpStatus.BAD_REQUEST, "이메일 형식이 올바르지 않습니다."), // 400 Bad Request
    PASSWORD_FORMAT_INVALID(HttpStatus.BAD_REQUEST, "비밀번호 형식이 올바르지 않습니다."), // 400 Bad Request
    PHONENUMBER_FORMAT_INVALID(HttpStatus.BAD_REQUEST, "핸드폰번호 형식이 올바르지 않습니다."), // 400 Bad Request
    NOTBLANK_FORMAT_INVALID(HttpStatus.BAD_REQUEST, "공백이 존재할 수 없습니다."), // 400 Bad Request

//    INVALID_TOKEN_CATEGORY(HttpStatus.BAD_REQUEST, "토큰 타입이 올바르지 않습니다."), // 400 Bad Request
//    BLANK_ACCESS_TOKEN(HttpStatus.UNAUTHORIZED, "액세스 토큰이 비었습니다."), // 401 Unauthorized
//    EXPIRED_ACCESS_TOKEN(HttpStatus.BAD_REQUEST, "액세스 토큰이 만료되었습니다."), // 400 Bad Request
//    INVALID_ACCESS_TOKEN(HttpStatus.BAD_REQUEST, "유효하지 않은 액세스 토큰입니다."), // 400 Bad Request
//
//    BLANK_REFRESH_TOKEN(HttpStatus.BAD_REQUEST, "리프레시 토큰이 비었습니다."), // 400 Bad Request
//    EXPIRED_REFRESH_TOKEN(HttpStatus.BAD_REQUEST, "리프레시 토큰이 만료되었습니다."), // 400 Bad Request
//    INVALID_REFRESH_TOKEN(HttpStatus.BAD_REQUEST, "유효하지 않은 리프레시 토큰입니다."), // 400 Bad Request

//    WEBSOCKET_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "처리 중 오류가 발생했습니다."),

    // Json 형식 오류
    JSON_FORMAT_INVALID(HttpStatus.BAD_REQUEST, "JSON 형식이 올바르지 않습니다."), // 400 Bad Request

    // 서버 에러
    SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 에러입니다."); // 500 Internal Server Error

    private final HttpStatus httpStatus;
    private final String errorMessage;
}