package pet_studio.pet_studio_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pet_studio.pet_studio_spring.domain.Mypet;
@Repository
public interface PetRepository extends JpaRepository<Mypet, Long> {
}
