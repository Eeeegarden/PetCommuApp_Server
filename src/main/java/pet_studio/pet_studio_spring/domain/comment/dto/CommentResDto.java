package pet_studio.pet_studio_spring.domain.comment.dto;

import lombok.*;
import pet_studio.pet_studio_spring.domain.comment.entity.Comment;

import java.time.LocalDateTime;

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