package pet_studio.pet_studio_spring.domain.board.dto;

import lombok.*;
import org.springframework.data.domain.Page;
import pet_studio.pet_studio_spring.domain.board.entity.Board;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardListDto {
    private Long id;
    private String email;
    private String nickName;
    private String content;
    private String profileImg;
    private String img;
    private int likeCount;
    private boolean likedByCurrentUser;
    private int commentCount;
    private LocalDateTime createdTime;
    private LocalDateTime updateTime;

    public static Page<BoardListDto> convertToDto(Page<Board> boardPage, String email) {
        return boardPage.map(board -> {
            boolean likedByCurrentUser = board.getLikes().stream()
                    .anyMatch(like -> like.getUser().getEmail().equals(email));

        return BoardListDto.builder()
                .id(board.getId())
                .email(board.getUser().getEmail())
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

