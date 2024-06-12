package pet_studio.pet_studio_spring.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pet_studio.pet_studio_spring.domain.User;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class SearchRepositoryImpl implements SearchRepository{

    private final EntityManager em;

    @Override
    public List<User> searchUsers(String keyword) {
        return em.createQuery("select u from User u where u.nickName like :nickName", User.class)
                .setParameter("nickName", "%"+keyword+"%")
                .getResultList();
    }


}