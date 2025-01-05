package pet_studio.pet_studio_spring.domain.search.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pet_studio.pet_studio_spring.domain.user.entity.User;
import pet_studio.pet_studio_spring.domain.user.dto.SimpleUserDto;
import pet_studio.pet_studio_spring.domain.search.repositoy.SearchRepository;
import pet_studio.pet_studio_spring.domain.user.repositoy.UserRepository;
import pet_studio.pet_studio_spring.domain.follow.service.FollowServiceImpl;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService{

    private final SearchRepository searchRepository;
    private final UserRepository userRepository;
    private final FollowServiceImpl followService;

    @Override
    public List<SimpleUserDto> searchUsers(String keyword) {
        List<User> userList = searchRepository.searchUsers(keyword);
        List<SimpleUserDto> userDtoList = new ArrayList<>();
        for (User u : userList) {
            userDtoList.add(new SimpleUserDto(u));
        }
        return userDtoList;
    }


}