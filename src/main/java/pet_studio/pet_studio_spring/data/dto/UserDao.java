package pet_studio.pet_studio_spring.data.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
public class UserDao {
    @Autowired
    private UserRepository repository;

    // 회원가입
    public UserDTO save(UserDTO userDTO){
        return repository.save(userDTO);
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

    // 회원삭제
    public void delete(int id){
        repository.deleteById(id);
    }

}
