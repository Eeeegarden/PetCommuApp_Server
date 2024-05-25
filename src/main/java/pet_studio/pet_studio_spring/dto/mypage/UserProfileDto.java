package pet_studio.pet_studio_spring.dto.mypage;


import lombok.*;
import pet_studio.pet_studio_spring.domain.Image;

@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserProfileDto {
    private String userId;
    private String nickName;
    private String userImageUrl;
    private int fromMeToOthersCnt;
    private int toMeFromOthersCnt;
    private String introduce;
}
