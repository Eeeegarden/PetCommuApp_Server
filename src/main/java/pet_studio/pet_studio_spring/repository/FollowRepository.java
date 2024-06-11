package pet_studio.pet_studio_spring.repository;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pet_studio.pet_studio_spring.domain.Follow;
import pet_studio.pet_studio_spring.domain.FollowStatus;
import pet_studio.pet_studio_spring.domain.User;

import java.util.List;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {

    boolean existsByFollowerAndFollowing(User follower, User following);

    void deleteByFollowerAndFollowing(User follower, User following);

    Page<Follow> findByFollowerAndStatus(User follower, FollowStatus status, Pageable pageable);

    Page<Follow> findByFollowingAndStatus(User follower, FollowStatus status, Pageable pageable);

    @Query("SELECT f.following FROM Follow f WHERE f.status = :status AND f.follower = :follower")
    List<User> findFollowingUsersByStatusAndFollower(@Param("status") FollowStatus status,
                                                     @Param("follower") User follower);


    boolean existsByStatusAndFollowerAndFollowing(FollowStatus status, User follower, User following);


}
