package pet_studio.pet_studio_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pet_studio.pet_studio_spring.domain.Image;
import pet_studio.pet_studio_spring.domain.User;

public interface ImageRepository extends JpaRepository<Image, Long> {
    Image findByUser(User user);
}
