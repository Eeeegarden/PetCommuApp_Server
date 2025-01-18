package pet_studio.pet_studio_spring.domain.follow.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pet_studio.pet_studio_spring.domain.follow.entity.Follow;
import pet_studio.pet_studio_spring.domain.follow.entity.FollowStatus;
import pet_studio.pet_studio_spring.domain.user.entity.User;
import pet_studio.pet_studio_spring.domain.follow.dto.FollowingDto;
import pet_studio.pet_studio_spring.global.error.code.CustomException;
import pet_studio.pet_studio_spring.domain.follow.repositoy.FollowRepository;
import pet_studio.pet_studio_spring.domain.user.repositoy.UserRepository;

import static pet_studio.pet_studio_spring.domain.follow.entity.FollowStatus.*;
import static pet_studio.pet_studio_spring.global.error.code.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class FollowServiceImpl implements FollowService {

    private final FollowRepository followRepository;
    private final UserRepository userRepository;

    public int followings(Long id) {
        return (int) followRepository.countByFollowing_id(id);
    }

    public int followers(Long id) {
        return (int) followRepository.countByFollower_id(id);
    }

    @Transactional
    public FollowStatus toggleFollow(String followingId, String email) {
        User follower = userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(NOT_FOUND_USER));
        User following = userRepository.findByEmail(followingId)
                .orElseThrow(() -> new CustomException(NOT_FOUND_USER));

        if (follower.getId().equals(following.getId())) {
            throw new CustomException(CANNOT_FOLLOW_YOURSELF);
        }

        if (followRepository.existsByFollowerAndFollowing(follower, following)) {
            followRepository.deleteByFollower_idAndFollowing_id(follower.getId(), following.getId());
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

    @Transactional
    public FollowStatus checkFollowStatus(String currentUserId, String email) {
        User currentUser = userRepository.findByEmail(currentUserId)
                .orElseThrow(() -> new CustomException(NOT_FOUND_USER));
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(NOT_FOUND_USER));

        Follow follow = followRepository.findByFollowerAndFollowing(currentUser, user)
                .orElse(null);

        if (follow == null) {
            return UNFOLLOWING;
        }

        return follow.getStatus();
    }

    public Page<FollowingDto> getFollowRequestsSentByUser(String email, Pageable pageable) {
        User follower = userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(NOT_FOUND_USER));

        Page<Follow> followRequestsSent = followRepository.findByFollowerAndStatus(follower, REQUESTED, pageable);

        return followRequestsSent.map(FollowingDto::convertToDTO);
    }

    public Page<FollowingDto> getFollowRequestsReceivedByUser(String email, Pageable pageable) {
        User following = userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(NOT_FOUND_USER));

        Page<Follow> followRequestsReceived = followRepository.findByFollowingAndStatus(following, REQUESTED, pageable);

        return followRequestsReceived.map(FollowingDto::convertToDTO);
    }

    @Transactional
    public FollowStatus acceptFollowRequest(Long followerId, String email){
        User user = userRepository.findByEmail(email)
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
    public void rejectFollowRequest(Long followingId, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(NOT_FOUND_USER));

        Follow followRequest = followRepository.findById(followingId)
                .orElseThrow(() -> new CustomException(NOT_FOUND_USER));

        if (!followRequest.getFollowing().equals(user)) {
            throw new CustomException(UNAUTHORIZED_ACCESS);
        }

        followRepository.delete(followRequest);
    }
}