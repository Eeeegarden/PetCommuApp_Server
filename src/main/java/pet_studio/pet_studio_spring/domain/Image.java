package pet_studio.pet_studio_spring.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String url;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_no")
    private User user;

    public void updateUrl(String url){
        this.url = url;
    }

}
