package pet_studio.pet_studio_spring.dto.user;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserDto {
    private String userId;
    private String userPassword;
    private String nickName;
    private boolean isPrivate;

    public boolean getisPrivate() {
        return isPrivate;
    }
}
