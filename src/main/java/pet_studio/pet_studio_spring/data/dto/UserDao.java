package pet_studio.pet_studio_spring.data.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import pet_studio.pet_studio_spring.data.dto.mypage.UserProfileDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
public class UserDao {
    @Autowired
    private UserRepository repository;

    // 회원가입
    public UserDTO save(UserDTO user){
        return repository.save(user);
    }
    
    // 모든유저 조회
    public List<UserDTO> getAllUsers(){
        List<UserDTO> users = new ArrayList<>();
        Streamable.of(repository.findAll())
                .forEach(users::add);
        return users;
    }
    
    // 로그인
    public UserDTO login(String userId, String userPassword){
        UserDTO user = repository.findByUserId(userId);
        if (user != null && user.getUserPassword().equals(userPassword)) {
            return user;
        } else {
            return null; 
        }
    }

    // 마이페이지

    public ResponseEntity myPageMain(@PathVariable("userId") String userId){
        UserDTO user = repository.findByUserId(userId);
        if (user == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        UserProfileDTO result = UserProfileDTO.builder()
                .nickName(user.getNickName())
                .userId(user.getUserId())
                .user_img(user.getImg())
                .introduce(user.getIntroduce())
                .build();

        return new ResponseEntity(result, HttpStatus.OK);

    }

    // 회원삭제
    public void delete(int id){
        repository.deleteById(id);
    }

}
