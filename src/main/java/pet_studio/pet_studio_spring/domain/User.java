package pet_studio.pet_studio_spring.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userNo;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "user_password")
    private String userPassword;

    @Column(name = "nick_name")
    private String nickName;

    @Column(name = "user_introduce")
    private String introduce;

    @Column(name = "user_img")
    private String img;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private Image image;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Mypet> mypet;

}
