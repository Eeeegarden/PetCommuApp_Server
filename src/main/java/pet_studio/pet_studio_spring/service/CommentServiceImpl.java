package pet_studio.pet_studio_spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pet_studio.pet_studio_spring.domain.Board;
import pet_studio.pet_studio_spring.domain.Comment;
import pet_studio.pet_studio_spring.domain.User;
import pet_studio.pet_studio_spring.dto.comment.CommentCreateDto;
import pet_studio.pet_studio_spring.dto.comment.CommentResDto;
import pet_studio.pet_studio_spring.exception.CustomException;
import pet_studio.pet_studio_spring.repository.BoardRepository;
import pet_studio.pet_studio_spring.repository.CommentRepository;
import pet_studio.pet_studio_spring.repository.FollowRepository;
import pet_studio.pet_studio_spring.repository.UserRepository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static pet_studio.pet_studio_spring.domain.FollowStatus.FOLLOWING;
import static pet_studio.pet_studio_spring.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;
    private final FollowRepository followRepository;

    @Transactional
    public CommentResDto createComment(Long boardId, CommentCreateDto commentCreateDto, String userId) {

        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new CustomException(NOT_FOUND_USER));

        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new CustomException(NOT_FOUND_POST));

        if (!Objects.equals(user.getUserId(), board.getUser().getUserId())) {
            boolean isFollowing = followRepository
                    .existsByStatusAndFollowerAndFollowing(FOLLOWING, user, board.getUser());
            if (!isFollowing) {
                throw new CustomException(UNAUTHORIZED_ACCESS);
            }
        }

        Comment comment = Comment.builder()
                .user(user)
                .board(board)
                .content(commentCreateDto.getContent())
                .build();

        Comment savedComment = commentRepository.save(comment);

        return CommentResDto.convertToDto(savedComment);
    }

    @Transactional
    public void deleteComment(Long commentId, String userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new CustomException(NOT_FOUND_USER));

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CustomException(NOT_FOUND_COMMENT));

        if (!Objects.equals(user.getUserId(), comment.getUser().getUserId())
                && !Objects.equals(user.getUserId(), comment.getBoard().getUser().getUserId())) {
            throw new CustomException(UNAUTHORIZED_ACCESS);
        }

        commentRepository.delete(comment);
    }

    @Transactional(readOnly = true)
    public List<CommentResDto> getComments(Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new CustomException(NOT_FOUND_POST));

        return commentRepository.findByBoard(board).stream()
                .map(CommentResDto::convertToDto)
                .collect(Collectors.toList());
    }
}