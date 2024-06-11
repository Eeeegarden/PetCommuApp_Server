package pet_studio.pet_studio_spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pet_studio.pet_studio_spring.domain.FollowStatus;
import pet_studio.pet_studio_spring.domain.User;
import pet_studio.pet_studio_spring.dto.follow.FollowingDto;
import pet_studio.pet_studio_spring.service.FollowService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/follow")
public class FollowController {

    private final FollowService followService;

    @PostMapping("/{followingId}")
    public ResponseEntity<FollowStatus> toggleFollow(
            @PathVariable Long followingId,
            @RequestParam String userId
    ) {
        return ResponseEntity.ok(followService.toggleFollow(followingId, userId));
    }

    @GetMapping("/sent")
    public ResponseEntity<Page<FollowingDto>> getFollowRequestsSentByUser(String userId,
                                                                          Pageable pageable) {

        return ResponseEntity.ok(followService.getFollowRequestsSentByUser(userId, pageable));
    }

    @GetMapping("/received")
    public ResponseEntity<Page<FollowingDto>> getFollowRequestsReceivedByUser(String userId,
                                                                              Pageable pageable) {

        return ResponseEntity.ok(followService.getFollowRequestsReceivedByUser(userId, pageable));
    }

    @PostMapping("/accept/{followerId}")
    public ResponseEntity<FollowStatus> acceptFollowRequest(
            @PathVariable Long followerId, String userId) {

        return ResponseEntity.ok(followService.acceptFollowRequest(followerId, userId));
    }

    @PostMapping("/reject/{followingId}")
    public void rejectFollowRequest(@PathVariable Long followingId, String userId) {

        followService.rejectFollowRequest(followingId, userId);
    }
}
