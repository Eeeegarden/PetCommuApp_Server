package pet_studio.pet_studio_spring.domain.board.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.LastModifiedDate;
import pet_studio.pet_studio_spring.domain.comment.entity.Comment;
import pet_studio.pet_studio_spring.domain.image.entity.Image;
import pet_studio.pet_studio_spring.domain.like.entity.Likes;
import pet_studio.pet_studio_spring.domain.user.entity.User;
import pet_studio.pet_studio_spring.global.common.BaseEntity;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "board")
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @Column(name = "MODIFIED_TIME")
    @LastModifiedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime modifiedTime;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<Likes> likes;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @Column(name = "like_count")
    private int likeCount;

    @Column(name = "comment_count")
    private int commentCount;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id")
    private Image image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_no")
    private User user;

    public int getCommentCount() {
        return comments.size();
    }

    // 좋아요 수 조회
    public int getLikeCount() {
        return likes.size();
    }


}