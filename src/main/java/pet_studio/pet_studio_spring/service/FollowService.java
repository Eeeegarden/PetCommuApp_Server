package pet_studio.pet_studio_spring.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pet_studio.pet_studio_spring.domain.FollowStatus;
import pet_studio.pet_studio_spring.dto.follow.FollowingDto;

public interface FollowService {
    int followings(Long userNo);
    int followers(Long userNo);
    FollowStatus toggleFollow(String followingId, String userId);
    FollowStatus checkFollowStatus(String currentUserId, String userId);
    Page<FollowingDto> getFollowRequestsSentByUser(String userId, Pageable pageable);
    Page<FollowingDto> getFollowRequestsReceivedByUser(String userId, Pageable pageable);
    FollowStatus acceptFollowRequest(Long followerId, String userId);
    void rejectFollowRequest(Long followingId, String userId);
}