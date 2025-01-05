package pet_studio.pet_studio_spring.domain.follow.entity;


import jakarta.persistence.*;
import lombok.*;
import pet_studio.pet_studio_spring.global.common.BaseEntity;
import pet_studio.pet_studio_spring.domain.user.entity.User;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Follow extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "follower_id", nullable = false)
    private User follower;

    @ManyToOne
    @JoinColumn(name = "following_id", nullable = false)
    private User following;

    @Enumerated(EnumType.STRING)
    private FollowStatus status;

}
