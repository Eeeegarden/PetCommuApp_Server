package pet_studio.pet_studio_spring.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pet_studio.pet_studio_spring.domain.User;

@Getter
@Setter
@ToString
public class SimpleUserDto {

    private String userId;
    private String nickname;
    private String img;
    @Builder
    public SimpleUserDto(User user) {
        this.userId = user.getUserId();
        this.nickname = user.getNickName();
        this.img = user.getImg();

    }
}