package pet_studio.pet_studio_spring.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import pet_studio.pet_studio_spring.domain.Image;
import pet_studio.pet_studio_spring.domain.User;
import pet_studio.pet_studio_spring.dto.follow.FollowerDto;
import pet_studio.pet_studio_spring.dto.follow.FollowingDto;
import pet_studio.pet_studio_spring.dto.user.UserDto;
import pet_studio.pet_studio_spring.dto.mypage.UserProfileDto;
import pet_studio.pet_studio_spring.dto.user.UserFollowListDto;
import pet_studio.pet_studio_spring.exception.CustomException;
import pet_studio.pet_studio_spring.exception.ErrorCode;
import pet_studio.pet_studio_spring.repository.ImageRepository;
import pet_studio.pet_studio_spring.repository.UserRepository;
import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static pet_studio.pet_studio_spring.domain.FollowStatus.FOLLOWING;
import static pet_studio.pet_studio_spring.exception.ErrorCode.ALREADY_EXIST_NICKNAME;
import static pet_studio.pet_studio_spring.exception.ErrorCode.ALREADY_EXIST_USER;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private ImageService imageService;
    @Autowired
    private FollowService followService;

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
                .type("profile")
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

        if (optionalUser.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        int followingCnt = followService.followings(optionalUser.get().getUserNo());
        int followerCnt = followService.followers(optionalUser.get().getUserNo());



        User user = optionalUser.get();
        UserProfileDto result = UserProfileDto.builder()
                .nickName(user.getNickName())
                .userId(user.getUserId())
                .userImageUrl(user.getImg())
                .introduce(user.getIntroduce())
                .followerCnt(followerCnt)
                .followingCnt(followingCnt)
                .build();

        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    // 팔로우,팔로잉 목록 조희
    public UserFollowListDto getFollowList(String userId) {
        User user = findUserById(userId);

        List<FollowingDto> followingDTOs = user.getFollowingList().stream()
                .filter(follow -> follow.getStatus() == FOLLOWING)
                .map(FollowingDto::convertToDTO)
                .toList();

        List<FollowerDto> followerDTOs = user.getFollowerList().stream()
                .filter(follow -> follow.getStatus() == FOLLOWING)
                .map(FollowerDto::convertToDTO)
                .toList();

        return UserFollowListDto.builder()
                .followerList(followerDTOs)
                .followingList(followingDTOs)
                .build();
    }

    public void validateUserId(String userId) {
        if (userId.length() < 7) {
            throw new CustomException(ErrorCode.INVALID_USER_ID);
        }
    }

    public void validateUserPw(String password) {
        if (password.length() < 7) {
            throw new CustomException(ErrorCode.INVALID_USER_PW);
        }
    }
}
