package pet_studio.pet_studio_spring.domain.user.dto;

import lombok.Data;

@Data
public class NicknameUpdateDto {
    private String userId;
    private String newNickname;
}
