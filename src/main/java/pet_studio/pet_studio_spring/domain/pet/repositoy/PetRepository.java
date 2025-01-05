package pet_studio.pet_studio_spring.domain.pet.repositoy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pet_studio.pet_studio_spring.domain.pet.entity.Mypet;
@Repository
public interface PetRepository extends JpaRepository<Mypet, Long> {
}
