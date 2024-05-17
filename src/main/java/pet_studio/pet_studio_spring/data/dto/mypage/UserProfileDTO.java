package pet_studio.pet_studio_spring.data.dto.mypage;


import lombok.*;
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserProfileDTO {
    private String userId;
    private String nickName;
    private String user_img;
    private int fromMeToOthersCnt;
    private int toMeFromOthersCnt;
    private String introduce;
}
