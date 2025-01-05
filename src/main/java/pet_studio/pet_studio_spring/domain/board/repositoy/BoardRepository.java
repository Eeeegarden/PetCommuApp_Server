package pet_studio.pet_studio_spring.domain.board.repositoy;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pet_studio.pet_studio_spring.domain.board.entity.Board;
import pet_studio.pet_studio_spring.domain.user.entity.User;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    Page<Board> findByUser(User user,Pageable pageable);
    Page<Board> findByUserIn(List<User> followingUsers, Pageable pageable);
}
