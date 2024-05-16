package pet_studio.pet_studio_spring.data.dto;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserDTO,Integer> {
    UserDTO findByUserId(String userId);
}
