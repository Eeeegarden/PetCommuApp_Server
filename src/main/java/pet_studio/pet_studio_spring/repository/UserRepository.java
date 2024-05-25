package pet_studio.pet_studio_spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pet_studio.pet_studio_spring.domain.User;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUserId(String userId);
    boolean existsByUserId(String userId);
    User findByNickName(String nickName);
}
