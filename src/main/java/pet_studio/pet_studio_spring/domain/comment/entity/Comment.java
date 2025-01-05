package pet_studio.pet_studio_spring.domain.comment.entity;

import jakarta.persistence.*;
import lombok.*;
import pet_studio.pet_studio_spring.domain.board.entity.Board;
import pet_studio.pet_studio_spring.global.common.BaseEntity;
import pet_studio.pet_studio_spring.domain.user.entity.User;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne
    @JoinColumn(name = "user_no")
    private User user;

}
