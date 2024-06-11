package pet_studio.pet_studio_spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pet_studio.pet_studio_spring.domain.Follow;
import pet_studio.pet_studio_spring.domain.FollowStatus;
import pet_studio.pet_studio_spring.domain.User;
import pet_studio.pet_studio_spring.dto.follow.FollowingDto;
import pet_studio.pet_studio_spring.exception.CustomException;
import pet_studio.pet_studio_spring.repository.FollowRepository;
import pet_studio.pet_studio_spring.repository.UserRepository;
import static pet_studio.pet_studio_spring.domain.FollowStatus.*;
import static pet_studio.pet_studio_spring.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class FollowServiceImpl implements FollowService {

    private final FollowRepository followRepository;
    private final UserRepository userRepository;

    public int followings(Long userNo){
        return (int) followRepository.countByFollowingUserNo(userNo);
    }
    public int followers(Long userNo){
        return (int) followRepository.countByFollowerUserNo(userNo);
    }

    @Transactional
    public FollowStatus toggleFollow(Long followingId,String userId) {
        User follower = userRepository.findByUserId(userId)
                .orElseThrow(() -> new CustomException(NOT_FOUND_USER));
        User following = userRepository.findById(followingId)
                .orElseThrow(() -> new CustomException(NOT_FOUND_USER));

        if (follower.getUserNo().equals(following.getUserNo())) {
            throw new CustomException(CANNOT_FOLLOW_YOURSELF);
        }

        if (followRepository.existsByFollowerAndFollowing(follower, following)) {
            followRepository.deleteByFollowerUserNoAndFollowingUserNo(follower.getUserNo(), following.getUserNo());
            return UNFOLLOWING;
        } else {
            if (following.getIsPrivate()) {
                Follow followRequest = new Follow();
                followRequest.setFollower(follower);
                followRequest.setFollowing(following);
                followRequest.setStatus(REQUESTED);

                followRepository.save(followRequest);

                return REQUESTED;
            }
            Follow follow = new Follow();
            follow.setFollower(follower);
            follow.setFollowing(following);
            follow.setStatus(FOLLOWING);

            followRepository.save(follow);

            return FOLLOWING;
        }
    }

    public Page<FollowingDto> getFollowRequestsSentByUser(String userId, Pageable pageable) {
        User follower = userRepository.findByUserId(userId)
                .orElseThrow(() -> new CustomException(NOT_FOUND_USER));

        Page<Follow> followRequestsSent = followRepository.findByFollowerAndStatus(follower, REQUESTED,
                pageable);

        return followRequestsSent.map(FollowingDto::convertToDTO);
    }

    public Page<FollowingDto> getFollowRequestsReceivedByUser(String userId,
                                                              Pageable pageable) {
        User following = userRepository.findByUserId(userId)
                .orElseThrow(() -> new CustomException(NOT_FOUND_USER));

        Page<Follow> followRequestsReceived = followRepository.findByFollowingAndStatus(following,
                REQUESTED, pageable);

        return followRequestsReceived.map(FollowingDto::convertToDTO);
    }

    @Transactional
    public FollowStatus acceptFollowRequest(Long followerId, String userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new CustomException(NOT_FOUND_USER));

        Follow followRequest = followRepository.findById(followerId)
                .orElseThrow(() -> new CustomException(NOT_FOUND_USER));

        if (!followRequest.getFollowing().equals(user)) {
            throw new CustomException(UNAUTHORIZED_ACCESS);
        }

        if (followRequest.getStatus() == FOLLOWING) {
            throw new CustomException(FOLLOW_REQUEST_ALREADY_ACCEPTED);
        }

        followRequest.setStatus(FOLLOWING);
        followRepository.save(followRequest);

        return FOLLOWING;
    }

    @Transactional
    public void rejectFollowRequest(Long followingId, String userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new CustomException(NOT_FOUND_USER));

        Follow followRequest = followRepository.findById(followingId)
                .orElseThrow(() -> new CustomException(NOT_FOUND_USER));

        if (!followRequest.getFollowing().equals(user)) {
            throw new CustomException(UNAUTHORIZED_ACCESS);
        }

        followRepository.delete(followRequest);
    }
}