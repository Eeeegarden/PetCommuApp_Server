package pet_studio.pet_studio_spring.service;

import pet_studio.pet_studio_spring.dto.comment.CommentCreateDto;
import pet_studio.pet_studio_spring.dto.comment.CommentResDto;

import java.util.List;

public interface CommentService {
    public CommentResDto createComment(Long boardId, CommentCreateDto commentCreateDto,
                                       String userId);
    public void deleteComment(Long commentId, String userId);
    public List<CommentResDto> getComments(Long boardId);
}
