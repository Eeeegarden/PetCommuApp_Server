package pet_studio.pet_studio_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pet_studio.pet_studio_spring.domain.Board;
@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
}
