package pet_studio.pet_studio_spring.domain.comment.repositoy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pet_studio.pet_studio_spring.domain.board.entity.Board;
import pet_studio.pet_studio_spring.domain.comment.entity.Comment;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByBoard(Board board);
}
