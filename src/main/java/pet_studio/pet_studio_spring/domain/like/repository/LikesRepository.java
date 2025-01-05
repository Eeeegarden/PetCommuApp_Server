package pet_studio.pet_studio_spring.domain.like.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pet_studio.pet_studio_spring.domain.board.entity.Board;
import pet_studio.pet_studio_spring.domain.like.entity.Likes;
import pet_studio.pet_studio_spring.domain.user.entity.User;

@Repository
public interface LikesRepository extends JpaRepository<Likes, Long> {
    Likes findByUserAndBoard(User user, Board board);
}
