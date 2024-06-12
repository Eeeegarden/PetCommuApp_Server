package pet_studio.pet_studio_spring.repository;

import pet_studio.pet_studio_spring.domain.User;

import java.util.List;

public interface UserRepositoryCustom {
    List<User> search(User user, String text);
}