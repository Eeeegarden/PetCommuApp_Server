package pet_studio.pet_studio_spring.domain.comment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pet_studio.pet_studio_spring.domain.board.entity.Board;
import pet_studio.pet_studio_spring.domain.comment.entity.Comment;
import pet_studio.pet_studio_spring.domain.user.entity.User;
import pet_studio.pet_studio_spring.domain.comment.dto.CommentCreateDto;
import pet_studio.pet_studio_spring.domain.comment.dto.CommentResDto;
import pet_studio.pet_studio_spring.global.error.code.CustomException;
import pet_studio.pet_studio_spring.domain.board.repositoy.BoardRepository;
import pet_studio.pet_studio_spring.domain.comment.repositoy.CommentRepository;
import pet_studio.pet_studio_spring.domain.follow.repositoy.FollowRepository;
import pet_studio.pet_studio_spring.domain.user.repositoy.UserRepository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static pet_studio.pet_studio_spring.domain.follow.entity.FollowStatus.FOLLOWING;
import static pet_studio.pet_studio_spring.global.error.code.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;
    private final FollowRepository followRepository;

    @Transactional
    public CommentResDto createComment(Long boardId, CommentCreateDto commentCreateDto, String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(NOT_FOUND_USER));

        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new CustomException(NOT_FOUND_POST));

        if (!Objects.equals(user.getEmail(), board.getUser().getEmail())) {
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
    public void deleteComment(Long commentId, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(NOT_FOUND_USER));

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CustomException(NOT_FOUND_COMMENT));

        if (!Objects.equals(user.getEmail(), comment.getUser().getEmail())
                && !Objects.equals(user.getEmail(), comment.getBoard().getUser().getEmail())) {
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