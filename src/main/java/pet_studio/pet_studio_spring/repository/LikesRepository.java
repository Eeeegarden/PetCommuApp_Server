package pet_studio.pet_studio_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pet_studio.pet_studio_spring.domain.Board;
import pet_studio.pet_studio_spring.domain.Likes;
import pet_studio.pet_studio_spring.domain.User;

@Repository
public interface LikesRepository extends JpaRepository<Likes, Long> {
    Likes findByUserAndBoard(User user, Board board);
}
