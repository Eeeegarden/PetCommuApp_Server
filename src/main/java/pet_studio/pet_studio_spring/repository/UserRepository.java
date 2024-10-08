package pet_studio.pet_studio_spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pet_studio.pet_studio_spring.domain.User;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long>,UserRepositoryCustom {
    Optional<User> findByUserId(String userId);
    boolean existsByUserId(String userId);
    Optional<User> findByNickName(String nickName);
    boolean existsByNickName(String nickName); // 닉네임 중복 여부 확인
}
