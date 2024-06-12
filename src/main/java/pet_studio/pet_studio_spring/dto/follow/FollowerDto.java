package pet_studio.pet_studio_spring.dto.follow;

import lombok.*;
import pet_studio.pet_studio_spring.domain.Follow;
import pet_studio.pet_studio_spring.domain.FollowStatus;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FollowerDto {

    private Long id;
    private Long followerId;
    private String nickName;
    private String profileImage;
    private FollowStatus status;

    public static FollowerDto convertToDTO(Follow follow) {
        return FollowerDto.builder()
                .id(follow.getId())
                .followerId(follow.getFollower().getUserNo())
                .nickName(follow.getFollower().getNickName())
                .profileImage(follow.getFollower().getImg())
                .status(follow.getStatus())
                .build();
    }
}