package pet_studio.pet_studio_spring.dto.comment;

import lombok.*;
import pet_studio.pet_studio_spring.domain.Comment;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentResDto {

    private String content;
    private String nickName;
    private String profileImage;
    private LocalDateTime createdTime;

    public static CommentResDto convertToDto(Comment comment) {
        return CommentResDto.builder()
                .content(comment.getContent())
                .nickName(comment.getUser().getNickName())
                .profileImage(comment.getUser().getImg())
                .createdTime(comment.getCreatedTime())
                .build();
    }
}