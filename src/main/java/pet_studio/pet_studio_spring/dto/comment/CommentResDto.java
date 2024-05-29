package pet_studio.pet_studio_spring.dto.comment;

import lombok.*;
import pet_studio.pet_studio_spring.domain.Comment;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentResDto {

    private String content;

    public static CommentResDto convertToDTO(Comment comment) {
        return CommentResDto.builder()
                .content(comment.getContent())
                .build();
    }
}