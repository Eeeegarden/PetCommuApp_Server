package pet_studio.pet_studio_spring.domain;

import jakarta.persistence.*;
import lombok.*;

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

//    @OneToMany(mappedBy = "parentComment", cascade = CascadeType.ALL)
//    private List<Reply> replies;

    @ManyToOne
    @JoinColumn(name = "user_no")
    private User user;

}
