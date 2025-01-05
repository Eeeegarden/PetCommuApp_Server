package pet_studio.pet_studio_spring.domain.search.service;

import pet_studio.pet_studio_spring.domain.user.dto.SimpleUserDto;

import java.util.List;

public interface SearchService {
    List<SimpleUserDto> searchUsers(String keyword);

}
