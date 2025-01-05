package pet_studio.pet_studio_spring.domain.search.repositoy;

import org.springframework.stereotype.Repository;
import pet_studio.pet_studio_spring.domain.user.entity.User;

import java.util.List;

@Repository
public interface SearchRepository {

    // 검색 키워드를 통해 유저 찾기
    public List<User> searchUsers(String keyword);

}
