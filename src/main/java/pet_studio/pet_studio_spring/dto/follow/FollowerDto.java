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
    private FollowStatus status;

    public static FollowerDto convertToDTO(Follow follow) {
        return FollowerDto.builder()
                .id(follow.getId())
                .followerId(follow.getFollower().getUserNo())
                .status(follow.getStatus())
                .build();
    }
}