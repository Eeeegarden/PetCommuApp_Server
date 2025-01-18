package pet_studio.pet_studio_spring.domain.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pet_studio.pet_studio_spring.domain.user.entity.User;

@Getter
@Setter
@ToString
public class SimpleUserDto {

    private String email;
    private String nickname;
    private String img;
    @Builder
    public SimpleUserDto(User user) {
        this.email = user.getEmail();
        this.nickname = user.getNickName();
        this.img = user.getImg();

    }
}