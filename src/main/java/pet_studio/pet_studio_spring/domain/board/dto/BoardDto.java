package pet_studio.pet_studio_spring.domain.board.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pet_studio.pet_studio_spring.domain.board.entity.Board;

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