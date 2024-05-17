package pet_studio.pet_studio_spring.data.dto;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name="user")
public class UserDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

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



}
