package pet_studio.pet_studio_spring.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pet_studio.pet_studio_spring.domain.Image;
import pet_studio.pet_studio_spring.domain.User;
import pet_studio.pet_studio_spring.dto.image.ImageResponseDto;
import pet_studio.pet_studio_spring.dto.image.ImageUploadDto;
import pet_studio.pet_studio_spring.repository.ImageRepository;
import pet_studio.pet_studio_spring.repository.UserRepository;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    private static final Logger logger = LoggerFactory.getLogger(ImageServiceImpl.class);

    private final ImageRepository imageRepository;
    private final UserRepository userRepository;

    @Value("${spring.file.profileImagePath}")
    private String uploadFolder;

    @Value("${spring.file.profileImagePath}")
    private String profileImageUploadFolder;

    @Value("${spring.file.boardImagePath}")
    private String boardImageUploadFolder;


    @Override
    public void upload(ImageUploadDto imageUploadDTO, String userId) {
        User user = userRepository.findByUserId(userId).orElseThrow(() -> new UsernameNotFoundException("이메일이 존재하지 않습니다."));
        MultipartFile file = imageUploadDTO.getFile();
        String type = imageUploadDTO.getType();

        UUID uuid = UUID.randomUUID();
        String imageFileName = uuid + "_" + file.getOriginalFilename();

        String uploadFolder = "profile".equals(type) ? profileImageUploadFolder : boardImageUploadFolder;
        File destinationFile = new File(uploadFolder + imageFileName);

        try {
            file.transferTo(destinationFile);
            String imageUrl = "profile".equals(type) ? "/profileImages/" + imageFileName : "/boardImages/" + imageFileName;
            Image image = imageRepository.findByUserAndType(user, type).orElse(null);

            if (image != null) {
                // 이미지가 이미 존재하면 url 업데이트
                image.updateUrl(imageUrl);
            } else {
                // 이미지가 없으면 객체 생성 후 저장
                image = Image.builder()
                        .user(user)
                        .url(imageUrl)
                        .type(type)
                        .build();
            }
            if ("profile".equals(image.getType())) {
                user.updateimg(image.getUrl());
            }
            imageRepository.save(image);

            // User 테이블의 img 필드 업데이트
            user.updateimg(imageUrl);
            userRepository.save(user);
            
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ImageResponseDto findImage(String userId) {
        User user = userRepository.findByUserId(userId).orElseThrow(() -> new UsernameNotFoundException("이메일이 존재하지 않습니다."));
        Image image = imageRepository.findByUserAndType(user, "profile").orElse(null);

        String defaultImageUrl = "/profileImages/ic_account.png";

        return ImageResponseDto.builder()
                .url(image != null ? image.getUrl() : defaultImageUrl)
                .build();
    }

    private static class UsernameNotFoundException extends RuntimeException {
        public UsernameNotFoundException(String message) {
            super(message);
        }
    }
}
