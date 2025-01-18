package pet_studio.pet_studio_spring.domain.user.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import pet_studio.pet_studio_spring.domain.follow.entity.Follow;
import pet_studio.pet_studio_spring.domain.image.entity.Image;
import pet_studio.pet_studio_spring.domain.pet.entity.Mypet;
import pet_studio.pet_studio_spring.domain.user.dto.SignUpDto;

import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "nick_name", nullable = false, unique = true)
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

    public static User createUser(SignUpDto signupDto, PasswordEncoder passwordEncoder) {
        return User.builder()
                .email(signupDto.email())
                .password(passwordEncoder.encode(signupDto.password()))
                .nickName(signupDto.nickName())
                .isPrivate(false)
                .introduce("한줄소개를 입력해주세요")
                .img("/profileImages/ic_account.png")
                .build();
    }

    public void updateImg(String img){
        this.img = img;
    }

    public void updateIntroduce(String introduce){this.introduce = introduce;}

}
