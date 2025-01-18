package pet_studio.pet_studio_spring.domain.user.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserDto {
    private String email;
    private String password;
    private String nickName;
}
