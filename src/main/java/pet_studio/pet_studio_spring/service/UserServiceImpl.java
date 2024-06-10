package pet_studio.pet_studio_spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import pet_studio.pet_studio_spring.domain.Image;
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
    @Autowired
    private ImageService imageService;

    public User findUserById(String id) {
        Optional<User> user = userRepository.findByUserId(id);
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
                .img("/profileImages/ic_account.png")
                .build();
        Image image = Image.builder()
                .url("/profileImages/ic_account.png")
                .user(user)
                .build();
        userRepository.save(user);

        return ResponseEntity.ok("사용자가 성공적으로 저장되었습니다.");
    }

    // 모든유저 조회
    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        userRepository.findAll().forEach(user -> {
            users.add(User.builder()
                    .userId(user.getUserId())
                    .userPassword(user.getUserPassword())
                    .nickName(user.getNickName())
                    .build());
        });
        return users;
    }

    // 로그인
    public User login(String userId, String userPassword) {
        Optional<User> optionalUser = userRepository.findByUserId(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.getUserPassword().equals(userPassword)) {
                // 패스워드 비교 후 일치하는 경우 사용자 정보를 User로 변환하여 반환
                return User.builder()
                        .userId(user.getUserId())
                        .nickName(user.getNickName())
                        .introduce(user.getIntroduce())
                        .build();
            }
        }
        // 로그인 실패 시 null 반환
        return null;
    }

    // 마이페이지

    public ResponseEntity<?> myPageMain(@PathVariable("userId") String userId){
        Optional<User> optionalUser = userRepository.findByUserId(userId);
        if (!optionalUser.isPresent()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        User user = optionalUser.get();
        UserProfileDto result = new UserProfileDto();
        result.setUserId(user.getUserId());
        result.setNickName(user.getNickName());
        result.setUserImageUrl(user.getImg());
        result.setIntroduce(user.getIntroduce());
        result.setFromMeToOthersCnt(0);
        result.setToMeFromOthersCnt(0);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // 닉네임 업데이트 메서드
    @Override
    public boolean updateNickname(String userId, String newNickname) {
        Optional<User> optionalUser = userRepository.findByUserId(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setNickName(newNickname);
            userRepository.save(user); // 업데이트된 사용자 저장
            return true; // 업데이트 성공
        }
        return false; // 사용자를 찾지 못함
    }

    // 한줄소개 업데이트 메서드
    @Override
    public boolean updateIntroduce(String userId, String newIntroduce) {
        Optional<User> optionalUser = userRepository.findByUserId(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setIntroduce(newIntroduce);
            userRepository.save(user); // 업데이트된 사용자 저장
            return true; // 업데이트 성공
        }
        return false; // 사용자를 찾지 못함
    }
}
