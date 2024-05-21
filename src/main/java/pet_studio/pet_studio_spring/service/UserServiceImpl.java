package pet_studio.pet_studio_spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import pet_studio.pet_studio_spring.domain.User;
import pet_studio.pet_studio_spring.dto.user.UserDto;
import pet_studio.pet_studio_spring.dto.mypage.UserProfileDto;
import pet_studio.pet_studio_spring.repository.UserRepository;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;


    public User findUserById(String id){
        Optional<User> user = Optional.ofNullable(userRepository.findByUserId(id));
        if (user.isPresent()) {
            return user.get();
        }
        return null;
    }
    // 회원가입
    public ResponseEntity<?> save(UserDto userDto) {
        // 아이디 중복 여부 확인
        if(userRepository.existsByUserId(userDto.getUserId())) {
            return ResponseEntity.badRequest().body("아이디 중복입니다.");
        }

        // UserDto를 User 엔터티로 변환
        User user = User.builder()
                .userId(userDto.getUserId())
                .userPassword(userDto.getUserPassword())
                .nickName(userDto.getNickName())
                .introduce("한줄소개를 입력해주세요")
                .build();

//        Image image = Image.builder()
//                .url("/profileImages/anonymous.png")
//                .user(user)
//                .build();
//        // 변환한 User 엔터티 저장
        userRepository.save(user);

        return ResponseEntity.ok("사용자가 성공적으로 저장되었습니다.");
    }
    
    // 모든유저 조회
    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        // UserRepository에서 가져온 User 엔터티를 UserDto로 변환하여 리스트에 추가
        userRepository.findAll().forEach(user -> {
            users.add(User.builder()
                    .userId(user.getUserId())
                    .userPassword(user.getUserPassword())
                    .nickName(user.getNickName())
                    // 다른 필드들도 필요한 경우 추가
                    .build());
        });
        return users;
    }
    
    // 로그인
    public User login(String userId, String userPassword) {
        User user = userRepository.findByUserId(userId);
        if (user != null && user.getUserPassword().equals(userPassword)) {
            // 패스워드 비교 후 일치하는 경우 사용자 정보를 User로 변환하여 반환
            return User.builder()
                    .userId(user.getUserId())
                    .nickName(user.getNickName())
                    .img(user.getImg())
                    .introduce(user.getIntroduce())
                    .build();
        } else {
            // 로그인 실패 시 null 반환
            return null;
        }
    }

    // 마이페이지

    public ResponseEntity myPageMain(@PathVariable("userId") String userId){
        User user = userRepository.findByUserId(userId);
        if (user == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        UserProfileDto result = UserProfileDto.builder()
                .nickName(user.getNickName())
                .userId(user.getUserId())
                .user_img(user.getImg())
                .introduce(user.getIntroduce())
                .build();

        return new ResponseEntity(result, HttpStatus.OK);

    }

    // 회원삭제
    public void delete(int id){
        userRepository.deleteById(id);
    }

}
