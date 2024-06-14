package pet_studio.pet_studio_spring.dto.board;

import lombok.*;
import org.springframework.data.domain.Page;
import pet_studio.pet_studio_spring.domain.Board;
import pet_studio.pet_studio_spring.domain.Image;
import pet_studio.pet_studio_spring.dto.likes.LikesDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardListDto {
    private Long id;
    private String userId;
    private String nickName;
    private String content;
    private String profileImg;
    private String img;
    private int likeCount;
    private boolean likedByCurrentUser;
    private int commentCount;
    private LocalDateTime createdTime;
    private LocalDateTime updateTime;

    public static Page<BoardListDto> convertToDto(Page<Board> boardPage, String userId) {
        return boardPage.map(board -> {
            boolean likedByCurrentUser = board.getLikes().stream()
                    .anyMatch(like -> like.getUser().getUserId().equals(userId));

        return BoardListDto.builder()
                .id(board.getId())
                .userId(board.getUser().getUserId())
                .nickName(board.getUser().getNickName())
                .content(board.getContent())
                .profileImg(board.getUser().getImg())
                .img(board.getImage().getUrl())
                .likeCount(board.getLikes().size())
                .likedByCurrentUser(likedByCurrentUser)
                .commentCount(board.getComments().size())
                .createdTime(board.getCreatedTime())
                .updateTime(board.getModifiedTime())
                .build();
        });
    }
}

