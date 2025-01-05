package pet_studio.pet_studio_spring.domain.comment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pet_studio.pet_studio_spring.domain.comment.dto.CommentCreateDto;
import pet_studio.pet_studio_spring.domain.comment.dto.CommentResDto;
import pet_studio.pet_studio_spring.domain.comment.service.CommentService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

  private final CommentService commentService;

  @PostMapping("/{boardId}")
  public ResponseEntity<CommentResDto> createComment(
          @PathVariable Long boardId,
          @RequestBody CommentCreateDto commentCreateDto,
          @RequestParam String userId
  ) {
    CommentResDto createdComment = commentService.createComment(boardId, commentCreateDto, userId);
    return ResponseEntity.ok(createdComment);
  }

  @DeleteMapping("/{commentId}")
  public void deleteComment(
          @PathVariable Long commentId,
          @RequestParam String userId
  ) {
    commentService.deleteComment(commentId, userId);
  }

  @GetMapping("/{boardId}")
  public ResponseEntity<List<CommentResDto>> getComments(@PathVariable Long boardId) {
    List<CommentResDto> comments = commentService.getComments(boardId);
    return ResponseEntity.ok(comments);
  }
}