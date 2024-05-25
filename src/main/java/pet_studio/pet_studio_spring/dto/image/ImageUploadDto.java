package pet_studio.pet_studio_spring.dto.image;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
@Data
public class ImageUploadDto {
    private MultipartFile file;
    private String userId;

}
