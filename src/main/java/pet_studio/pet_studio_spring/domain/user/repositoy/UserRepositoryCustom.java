package pet_studio.pet_studio_spring.domain.user.repositoy;

import pet_studio.pet_studio_spring.domain.user.entity.User;

import java.util.List;

public interface UserRepositoryCustom {
    List<User> search(User user, String text);
}