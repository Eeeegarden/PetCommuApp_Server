package pet_studio.pet_studio_spring.domain.user.repositoy;

import lombok.RequiredArgsConstructor;
import pet_studio.pet_studio_spring.domain.user.entity.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public List<User> search(User user, String text) {
        return em.createQuery("SELECT f.following " +
                        "FROM Follow f " +
                        "WHERE f.follower.id = :email " +
                        "AND f.following.nickName LIKE CONCAT(:text, '%') " +
                        "ORDER BY f.following.nickName ASC", User.class)
                .setParameter("email", user.getId())
                .setParameter("text", text)
                .setFirstResult(0)
                .setMaxResults(5)
                .getResultList();
    }
}