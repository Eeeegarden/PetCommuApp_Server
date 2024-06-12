package pet_studio.pet_studio_spring.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userNo;

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

    // 비공개 계정 -> 팔로우 요청, 거절
    @Column(nullable = false)
    private Boolean isPrivate;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Image> images;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Mypet> mypet;

    @OneToMany(mappedBy = "follower", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Follow> followingList;

    @OneToMany(mappedBy = "following", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Follow> followerList;

    public void updateimg(String img){
        this.img = img;
    }
    public void updateintroduce(String introduce){this.introduce = introduce;}

}
