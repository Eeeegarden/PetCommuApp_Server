package pet_studio.pet_studio_spring.domain.user.repositoy;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pet_studio.pet_studio_spring.domain.user.entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long>,UserRepositoryCustom {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    Optional<User> findByNickName(String nickName);
    boolean existsByNickName(String nickName); // 닉네임 중복 여부 확인
}
