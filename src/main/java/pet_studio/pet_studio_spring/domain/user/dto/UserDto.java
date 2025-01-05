package pet_studio.pet_studio_spring.domain.user.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserDto {
    private String userId;
    private String userPassword;
    private String nickName;
}
