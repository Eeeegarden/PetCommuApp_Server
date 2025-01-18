package pet_studio.pet_studio_spring.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import pet_studio.pet_studio_spring.domain.auth.service.AuthService;
import pet_studio.pet_studio_spring.domain.follow.dto.FollowerDto;
import pet_studio.pet_studio_spring.domain.follow.dto.FollowingDto;
import pet_studio.pet_studio_spring.domain.user.dto.UserFollowListDto;
import pet_studio.pet_studio_spring.domain.user.entity.User;
import pet_studio.pet_studio_spring.domain.user.dto.UserProfileDto;
import pet_studio.pet_studio_spring.domain.user.repositoy.UserRepository;
import pet_studio.pet_studio_spring.domain.image.service.ImageService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static pet_studio.pet_studio_spring.domain.follow.entity.FollowStatus.FOLLOWING;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService  {

    private final UserRepository userRepository;
    private final AuthService authService;
    private final ImageService imageService;
    private final PasswordEncoder passwordEncoder;

    // 모든유저 조회
    public List<User> getAllUsers() {

        List<User> users = new ArrayList<>();

        userRepository.findAll().forEach(user -> {
            users.add(User.builder()
                    .email(user.getEmail())
                    .password(user.getPassword())
                    .nickName(user.getNickName())
                    .build());
        });
        return users;
    }



    // 마이페이지
    public ResponseEntity<?> myPageMain(@PathVariable("email") String email){

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 유저입니다."));

        UserProfileDto result = new UserProfileDto();
//        result.setUserId(user.getEmail());
        result.setNickName(user.getNickName());
        result.setUserImageUrl(user.getImg());
        result.setIntroduce(user.getIntroduce());
        result.setFollowingCnt(0);
        result.setFollowerCnt(0);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // 닉네임 업데이트 메서드
    public boolean updateNickname(String email, String newNickname) {
        // 새로운 닉네임이 이미 다른 사용자에게 사용 중이거나 기존 닉네임과 같은지 확인
        if (!authService.isNicknameAvailable(newNickname) || userRepository.existsByNickName(newNickname)) {
            return false; // 중복된 경우 업데이트를 거부
        }
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
//            user.setNickName(newNickname);
            userRepository.save(user); // 업데이트된 사용자 저장
            return true; // 업데이트 성공
        }
        return false; // 사용자를 찾지 못함
    }

    // 한줄소개 업데이트 메서드
    public boolean updateIntroduce(String email, String newIntroduce) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
//            user.setIntroduce(newIntroduce);
            userRepository.save(user); // 업데이트된 사용자 저장
            return true; // 업데이트 성공
        }
        return false; // 사용자를 찾지 못함
    }


    // 팔로우,팔로잉 목록 조희
    public UserFollowListDto getFollowList(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 유저입니다."));


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


}
