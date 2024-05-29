package pet_studio.pet_studio_spring.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${spring.file.profileImagePath}")
    private String profileImageUploadFolder;

    @Value("${spring.file.boardImagePath}")
    private String boardImageUploadFolder;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 프로필 이미지 폴더 처리
        registry.addResourceHandler("/profileImages/**")
                .addResourceLocations("file:///" + profileImageUploadFolder);

        // 게시물 이미지 폴더 처리
        registry.addResourceHandler("/boardImages/**")
                .addResourceLocations("file:///" + boardImageUploadFolder);
    }
}
