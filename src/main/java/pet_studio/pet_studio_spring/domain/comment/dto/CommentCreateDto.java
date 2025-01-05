package pet_studio.pet_studio_spring.domain.comment.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentCreateDto {
    private String content;
}