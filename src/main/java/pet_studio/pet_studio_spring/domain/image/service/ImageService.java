package pet_studio.pet_studio_spring.domain.image.service;

import pet_studio.pet_studio_spring.domain.image.dto.ImageResponseDto;
import pet_studio.pet_studio_spring.domain.image.dto.ImageUploadDto;

public interface ImageService {
    void upload(ImageUploadDto imageUploadDto, String email);
    ImageResponseDto findImage(String email);
}
