package pet_studio.pet_studio_spring.domain.follow.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pet_studio.pet_studio_spring.domain.follow.entity.FollowStatus;
import pet_studio.pet_studio_spring.domain.follow.dto.FollowingDto;
import pet_studio.pet_studio_spring.domain.follow.service.FollowService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/follow")
public class FollowController {

    private final FollowService followService;

    @PostMapping("/{followingId}")
    public ResponseEntity<FollowStatus> toggleFollow(
            @PathVariable String followingId,
            @RequestParam String email
    ) {
        return ResponseEntity.ok(followService.toggleFollow(followingId, email));
    }

    @GetMapping("/status")
    public ResponseEntity<FollowStatus> checkFollowStatus(
            @RequestParam String currentUserId,
            @RequestParam String email
    ) {
        return ResponseEntity.ok(followService.checkFollowStatus(currentUserId, email));
    }

    @GetMapping("/sent")
    public ResponseEntity<Page<FollowingDto>> getFollowRequestsSentByUser(String email,
                                                                          Pageable pageable) {
        return ResponseEntity.ok(followService.getFollowRequestsSentByUser(email, pageable));
    }

    @GetMapping("/received")
    public ResponseEntity<Page<FollowingDto>> getFollowRequestsReceivedByUser(String email,
                                                                              Pageable pageable) {
        return ResponseEntity.ok(followService.getFollowRequestsReceivedByUser(email, pageable));
    }

    @PostMapping("/accept/{followerId}")
    public ResponseEntity<FollowStatus> acceptFollowRequest(
            @PathVariable Long followerId, String email) {
        return ResponseEntity.ok(followService.acceptFollowRequest(followerId, email));
    }

    @PostMapping("/reject/{followingId}")
    public void rejectFollowRequest(@PathVariable Long followingId, String email) {
        followService.rejectFollowRequest(followingId, email);
    }
}