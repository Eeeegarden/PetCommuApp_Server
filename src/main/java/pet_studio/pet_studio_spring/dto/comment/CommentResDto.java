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
    private String createdTime;

    public static CommentResDto convertToDto(Comment comment) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return CommentResDto.builder()
                .content(comment.getContent())
                .nickName(comment.getUser().getNickName())
                .profileImage(comment.getUser().getImg())
                .createdTime(comment.getCreatedAt().format(DateTimeFormatter.ISO_DATE_TIME))
                .build();
    }
}