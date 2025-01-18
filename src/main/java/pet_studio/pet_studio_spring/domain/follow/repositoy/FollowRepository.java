package pet_studio.pet_studio_spring.domain.follow.repositoy;

import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pet_studio.pet_studio_spring.domain.follow.entity.Follow;
import pet_studio.pet_studio_spring.domain.follow.entity.FollowStatus;
import pet_studio.pet_studio_spring.domain.user.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {

    long countByFollowing_id(Long id);
    long countByFollower_id(Long id);
    Optional<Follow> findByFollowerAndFollowing(User follower, User following);

    boolean existsByFollowerAndFollowing(User follower, User following);

    void deleteByFollower_idAndFollowing_id(Long followerNo, Long followingNo);

    Page<Follow> findByFollowerAndStatus(User follower, FollowStatus status, Pageable pageable);

    Page<Follow> findByFollowingAndStatus(User follower, FollowStatus status, Pageable pageable);

    @Query("SELECT f.following FROM Follow f WHERE f.status = :status AND f.follower = :follower")
    List<User> findFollowingUsersByStatusAndFollower(@Param("status") FollowStatus status,
                                                     @Param("follower") User follower);


    boolean existsByStatusAndFollowerAndFollowing(FollowStatus status, User follower, User following);


}
