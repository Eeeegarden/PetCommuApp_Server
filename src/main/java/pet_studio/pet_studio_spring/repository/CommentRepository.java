package pet_studio.pet_studio_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pet_studio.pet_studio_spring.domain.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
