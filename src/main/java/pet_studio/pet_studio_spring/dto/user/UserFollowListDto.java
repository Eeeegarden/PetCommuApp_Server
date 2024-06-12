package pet_studio.pet_studio_spring.dto.user;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pet_studio.pet_studio_spring.dto.follow.FollowerDto;
import pet_studio.pet_studio_spring.dto.follow.FollowingDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserFollowListDto {

    private List<FollowingDto> followingList;
    private List<FollowerDto> followerList;
}