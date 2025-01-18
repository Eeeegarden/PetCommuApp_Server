package pet_studio.pet_studio_spring.domain.follow.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pet_studio.pet_studio_spring.domain.follow.entity.FollowStatus;
import pet_studio.pet_studio_spring.domain.follow.dto.FollowingDto;

public interface FollowService {
    int followings(Long id);
    int followers(Long id);
    FollowStatus toggleFollow(String followingId, String email);
    FollowStatus checkFollowStatus(String currentUserId, String email);
    Page<FollowingDto> getFollowRequestsSentByUser(String email, Pageable pageable);
    Page<FollowingDto> getFollowRequestsReceivedByUser(String email, Pageable pageable);
    FollowStatus acceptFollowRequest(Long followerId, String email);
    void rejectFollowRequest(Long followingId, String email);
}