package pet_studio.pet_studio_spring.dto.board;

import lombok.*;
import org.springframework.data.domain.Page;
import pet_studio.pet_studio_spring.domain.Board;
import pet_studio.pet_studio_spring.domain.Image;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardListDto {
    private String nickName;
    private String content;
    private String profileImg;
    private String img;
    private int likeCount;
    private int commentCount;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;

    public static Page<BoardListDto> convertToDTO(Page<Board> boardPage) {
        return boardPage.map(board -> BoardListDto.builder()
                .nickName(board.getUser().getNickName())
                .content(board.getContent())
                .profileImg(board.getUser().getImg())
                .img(board.getImage().getUrl())
                .likeCount(board.getLikes().size())
                .commentCount(board.getComments().size())
                .createdAt(board.getCreatedAt())
                .updateAt(board.getUpdateAt())
                .build());
    }
}

