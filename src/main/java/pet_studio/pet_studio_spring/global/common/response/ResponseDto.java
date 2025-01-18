package pet_studio.pet_studio_spring.global.common.response;


public record ResponseDto<T>(
        int status,
        String message,
        T body
) {
    public static <T> ResponseDto<T> of(int status, String message, T body) {
        return new ResponseDto<>(status, message, body);
    }
}
