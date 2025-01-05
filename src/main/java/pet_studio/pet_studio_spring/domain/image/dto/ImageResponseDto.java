package pet_studio.pet_studio_spring.domain.image.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ImageResponseDto {

    private String url;

    @Builder
    public ImageResponseDto(String url){
        this.url = url;
    }

}
