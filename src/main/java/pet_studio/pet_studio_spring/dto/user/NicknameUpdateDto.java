package pet_studio.pet_studio_spring.dto.user;

import lombok.Data;

@Data
public class NicknameUpdateDto {
    private String userId;
    private String newNickname;
}
