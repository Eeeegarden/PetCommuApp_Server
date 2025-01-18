package pet_studio.pet_studio_spring.domain.comment.service;

import pet_studio.pet_studio_spring.domain.comment.dto.CommentCreateDto;
import pet_studio.pet_studio_spring.domain.comment.dto.CommentResDto;

import java.util.List;

public interface CommentService {
    public CommentResDto createComment(Long boardId, CommentCreateDto commentCreateDto,
                                       String email);
    public void deleteComment(Long commentId, String email);
    public List<CommentResDto> getComments(Long boardId);
}
