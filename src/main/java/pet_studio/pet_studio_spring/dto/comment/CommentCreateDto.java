package pet_studio.pet_studio_spring.dto.comment;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentCreateDto {
    private String content;
}