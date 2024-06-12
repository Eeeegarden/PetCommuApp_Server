package pet_studio.pet_studio_spring.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FollowStatus {
    FOLLOWING,
    UNFOLLOWING,
    REQUESTED
}