package pet_studio.pet_studio_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pet_studio.pet_studio_spring.domain.Mypet;

public interface PetRepository extends JpaRepository<Mypet, Long> {
}
