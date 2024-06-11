package pet_studio.pet_studio_spring.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pet_studio.pet_studio_spring.domain.FollowStatus;
import pet_studio.pet_studio_spring.dto.follow.FollowingDto;

public interface FollowService {
    public int followings(Long userNo);
    public int followers(Long userNo);
    public FollowStatus toggleFollow(Long followingId, String userId);
    public Page<FollowingDto> getFollowRequestsSentByUser(String userId, Pageable pageable);
    public Page<FollowingDto> getFollowRequestsReceivedByUser(String userId,Pageable pageable);
    public FollowStatus acceptFollowRequest(Long followerId, String userId);
    public void rejectFollowRequest(Long followingId, String userId);

}

