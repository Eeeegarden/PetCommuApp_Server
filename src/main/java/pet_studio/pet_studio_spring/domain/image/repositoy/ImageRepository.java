package pet_studio.pet_studio_spring.domain.image.repositoy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pet_studio.pet_studio_spring.domain.image.entity.Image;
import pet_studio.pet_studio_spring.domain.user.entity.User;

import java.util.Optional;
@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    Image findByUser(User user);
    Optional<Image> findByUserAndType(User user, String type);

}
