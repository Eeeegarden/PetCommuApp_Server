package pet_studio.pet_studio_spring.dto.board;

import lombok.*;
import pet_studio.pet_studio_spring.domain.Board;
import pet_studio.pet_studio_spring.dto.likes.LikesDto;

import java.time.LocalDateTime;
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
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public static BoardDetailDto convertToDTO(Board board) {
        List<LikesDto> likesDtos = board.getLikes().stream()
                .map(likes -> LikesDto.builder()
                        .nickName(likes.getUser().getNickName())
                        .build())
                .collect(Collectors.toList());


        return BoardDetailDto.builder()
                .nickName(board.getUser().getNickName())
                .img(board.getImage().getUrl())
                .content(board.getContent())
                .likes(likesDtos)
                .createdAt(board.getCreatedAt())
                .modifiedAt(board.getUpdateAt())
                .build();
    }
}
