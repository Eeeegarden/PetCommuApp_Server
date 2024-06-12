package pet_studio.pet_studio_spring.service;

import pet_studio.pet_studio_spring.dto.user.SimpleUserDto;

import java.util.List;

public interface SearchService {
    List<SimpleUserDto> searchUsers(String keyword);

}
