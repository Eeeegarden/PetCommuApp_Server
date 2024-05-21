package pet_studio.pet_studio_spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pet_studio.pet_studio_spring.domain.User;
import pet_studio.pet_studio_spring.dto.user.UserDto;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User,Integer> {
    User findByUserId(String userId);
    boolean existsByUserId(String userId);

    User findByNickName(String nickName);

}
