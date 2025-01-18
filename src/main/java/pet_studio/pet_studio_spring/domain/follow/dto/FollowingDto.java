package pet_studio.pet_studio_spring.domain.follow.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pet_studio.pet_studio_spring.domain.follow.entity.Follow;
import pet_studio.pet_studio_spring.domain.follow.entity.FollowStatus;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FollowingDto {

    private Long id;
    private Long followingId;
    private String nickName;
    private String profileImage;
    private FollowStatus status;

    public static FollowingDto convertToDTO(Follow follow) {
        return FollowingDto.builder()
                .id(follow.getId())
                .followingId(follow.getFollowing().getId())
                .nickName(follow.getFollowing().getNickName())
                .profileImage(follow.getFollowing().getImg())
                .status(follow.getStatus())
                .build();
    }
}