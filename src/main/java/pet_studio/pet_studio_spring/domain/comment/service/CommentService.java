package pet_studio.pet_studio_spring.domain.comment.service;

import pet_studio.pet_studio_spring.domain.comment.dto.CommentCreateDto;
import pet_studio.pet_studio_spring.domain.comment.dto.CommentResDto;

import java.util.List;

public interface CommentService {
    public CommentResDto createComment(Long boardId, CommentCreateDto commentCreateDto,
                                       String userId);
    public void deleteComment(Long commentId, String userId);
    public List<CommentResDto> getComments(Long boardId);
}
