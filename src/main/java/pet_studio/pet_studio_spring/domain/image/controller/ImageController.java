package pet_studio.pet_studio_spring.domain.image.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pet_studio.pet_studio_spring.domain.image.dto.ImageResponseDto;
import pet_studio.pet_studio_spring.domain.image.dto.ImageUploadDto;
import pet_studio.pet_studio_spring.domain.image.service.ImageService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/image")
public class ImageController {
    private final ImageService imageService;
    private static final Logger logger = LoggerFactory.getLogger(ImageController.class);
    @PostMapping("/upload")
    public ResponseEntity<String> upload(
            @RequestPart("email") String email,
            @RequestPart("file") MultipartFile file,
            @RequestPart("type") String type) {
        ImageUploadDto imageUploadDto = new ImageUploadDto();
        imageUploadDto.setEmail(email);
        imageUploadDto.setFile(file);
        imageUploadDto.setType(type);

        try {
            imageService.upload(imageUploadDto, email);
            return ResponseEntity.ok().body("이미지 업로드에 성공했습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("이미지 업로드에 실패했습니다.");
        }
    }

    @GetMapping("/{email}")
    public ResponseEntity<ImageResponseDto> getImage(@PathVariable String email) {
        try {
            ImageResponseDto imageResponseDto = imageService.findImage(email);
            return ResponseEntity.ok(imageResponseDto);
        } catch (Exception e) {
            logger.error("Error fetching image", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
