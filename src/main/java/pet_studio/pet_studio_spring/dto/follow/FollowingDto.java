package pet_studio.pet_studio_spring.dto.follow;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pet_studio.pet_studio_spring.domain.Follow;
import pet_studio.pet_studio_spring.domain.FollowStatus;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FollowingDto {

    private Long id;
    private Long followingId;
    private FollowStatus status;

    public static FollowingDto convertToDTO(Follow follow) {
        return FollowingDto.builder()
                .id(follow.getId())
                .followingId(follow.getFollowing().getUserNo())
                .status(follow.getStatus())
                .build();
    }
}