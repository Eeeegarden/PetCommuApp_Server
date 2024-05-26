package pet_studio.pet_studio_spring.dto.board;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardWriteRequestDto {
    private MultipartFile file;
    private String content;
}
