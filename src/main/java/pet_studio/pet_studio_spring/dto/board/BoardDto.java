package pet_studio.pet_studio_spring.dto.board;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pet_studio.pet_studio_spring.domain.Board;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardDto {

  private String nickName;
  private String content;
  private String img;
  private LocalDateTime createdTime;
  private LocalDateTime modifiedTime;

  public static BoardDto convertToDto(Board board) {

    return BoardDto.builder()
        .nickName(board.getUser().getNickName())
        .content(board.getContent())
        .img(board.getUser().getImg())
        .createdTime(board.getCreatedTime())
        .modifiedTime(board.getModifiedTime())
        .build();
  }
}