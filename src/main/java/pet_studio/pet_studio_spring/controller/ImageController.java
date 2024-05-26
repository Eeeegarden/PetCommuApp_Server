package pet_studio.pet_studio_spring.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pet_studio.pet_studio_spring.dto.image.ImageResponseDto;
import pet_studio.pet_studio_spring.dto.image.ImageUploadDto;
import pet_studio.pet_studio_spring.service.ImageService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/image")
public class ImageController {
    private final ImageService imageService;
    private static final Logger logger = LoggerFactory.getLogger(ImageController.class);
    @PostMapping("/upload")
    public ResponseEntity<String> upload(
            @RequestPart("userId") String userId,
            @RequestPart("file") MultipartFile file,
            @RequestPart("type") String type) {
        ImageUploadDto imageUploadDto = new ImageUploadDto();
        imageUploadDto.setUserId(userId);
        imageUploadDto.setFile(file);
        imageUploadDto.setType(type);

        try {
            imageService.upload(imageUploadDto, userId);
            return ResponseEntity.ok().body("이미지 업로드에 성공했습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("이미지 업로드에 실패했습니다.");
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ImageResponseDto> getImage(@PathVariable String userId) {
        try {
            ImageResponseDto imageResponseDto = imageService.findImage(userId);
            return ResponseEntity.ok(imageResponseDto);
        } catch (Exception e) {
            logger.error("Error fetching image", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
