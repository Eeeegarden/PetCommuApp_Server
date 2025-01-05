package pet_studio.pet_studio_spring.domain.user.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import pet_studio.pet_studio_spring.domain.user.entity.User;
import pet_studio.pet_studio_spring.domain.user.dto.UserDto;
import pet_studio.pet_studio_spring.domain.user.dto.UserFollowListDto;

import java.util.List;


public interface UserService {

    public User findUserById(String userid);
    public ResponseEntity<?> save(UserDto user);

    public List<User> getAllUsers();

    public User login(String userId, String userPassword);

    public ResponseEntity myPageMain(@PathVariable("userId") String userId);
    public UserFollowListDto getFollowList(String userId);


    // 닉네임 업데이트 메서드 추가
    boolean updateNickname(String userId, String newNickname);

    // 중복 닉네임 확인 메서드
    boolean isNicknameAvailable(String newNickname);

    // 한줄소개 업데이트 메서드 추가
    boolean updateIntroduce(String userId, String newIntroduce);
}