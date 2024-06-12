package pet_studio.pet_studio_spring.repository;

import lombok.RequiredArgsConstructor;
import pet_studio.pet_studio_spring.domain.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public List<User> search(User user, String text) {
        return em.createQuery("SELECT f.toUser " +
                        "FROM Follow f " +
                        "WHERE f.fromUser.userNo = :userId " +
                        "AND f.toUser.nickName LIKE CONCAT(:text, '%') " +
                        "ORDER BY f.toUser.nickName ASC", User.class)
                .setParameter("userId", user.getUserNo())
                .setParameter("text", text)
                .setFirstResult(0)
                .setMaxResults(5)
                .getResultList();
    }
}