package pet_studio.pet_studio_spring.domain.user.dto;


import lombok.*;

@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserProfileDto {
    private String email;
    private String nickName;
    private String userImageUrl;
    private int followingCnt;
    private int followerCnt;
    private String introduce;
}
