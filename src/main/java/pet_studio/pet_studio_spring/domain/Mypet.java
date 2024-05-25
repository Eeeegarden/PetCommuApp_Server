package pet_studio.pet_studio_spring.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "mypet")
public class Mypet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pet_name")
    private String petname;

    @Column(name = "pet_type")
    private String pettype;

    @Column(name = "pet_description")
    private String petdescription;

    @Column(name = "pet_introduce")
    private String petintroduce;

    @Column(name = "pet_img")
    private String petimg;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_no")
    private User user;


}
