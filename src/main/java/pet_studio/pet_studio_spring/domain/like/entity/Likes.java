package pet_studio.pet_studio_spring.domain.like.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pet_studio.pet_studio_spring.domain.board.entity.Board;
import pet_studio.pet_studio_spring.domain.user.entity.User;
import pet_studio.pet_studio_spring.global.common.BaseEntity;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Likes extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_no")
    private User user;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @Column(nullable = false)
    private Boolean isLike;
}
