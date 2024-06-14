package pet_studio.pet_studio_spring.dto.board;

import lombok.*;
import pet_studio.pet_studio_spring.domain.Board;
import pet_studio.pet_studio_spring.domain.Comment;
import pet_studio.pet_studio_spring.dto.comment.CommentDto;
import pet_studio.pet_studio_spring.dto.likes.LikesDto;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardDetailDto {

    private String nickName;
    private String img;
    private String content;
    private List<LikesDto> likes;
    private List<CommentDto> comments;
    private LocalDateTime createdTime;
    private LocalDateTime modifiedTime;

    public static BoardDetailDto convertToDto(Board board) {
        List<LikesDto> likesDtos = board.getLikes().stream()
                .map(likes -> LikesDto.builder()
                        .nickName(likes.getUser().getNickName())
                        .build())
                .collect(Collectors.toList());
        List<CommentDto> commentDTOS = board.getComments().stream()
                .sorted(Comparator.comparing(Comment::getCreatedTime).reversed())
                .map(comment -> {
                    return CommentDto.builder()
                            .nickName(comment.getUser().getNickName())
                            .content(comment.getContent())
                            .createdTime(comment.getCreatedTime())
                            .build();
                })
                .collect(Collectors.toList());


        return BoardDetailDto.builder()
                .nickName(board.getUser().getNickName())
                .img(board.getImage().getUrl())
                .content(board.getContent())
                .likes(likesDtos)
                .createdTime(board.getCreatedTime())
                .modifiedTime(board.getModifiedTime())
                .build();
    }
}
