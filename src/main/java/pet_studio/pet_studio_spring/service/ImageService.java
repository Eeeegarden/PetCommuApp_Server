package pet_studio.pet_studio_spring.service;

import pet_studio.pet_studio_spring.dto.image.ImageResponseDto;
import pet_studio.pet_studio_spring.dto.image.ImageUploadDto;

public interface ImageService {
    void upload(ImageUploadDto imageUploadDto, String userId);
    ImageResponseDto findImage(String userId);
}
