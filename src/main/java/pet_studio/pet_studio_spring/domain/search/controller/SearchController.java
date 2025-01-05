package pet_studio.pet_studio_spring.domain.search.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pet_studio.pet_studio_spring.domain.user.dto.SimpleUserDto;
import pet_studio.pet_studio_spring.domain.search.service.SearchServiceImpl;

import java.util.*;

/**
 * Search 관련 Controller
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/search")
public class SearchController {

    private final SearchServiceImpl searchService;

    @GetMapping("/user")
    public ResponseEntity<List<SimpleUserDto>> searchUser(@RequestParam("keyword") String keyword){
        List<SimpleUserDto> userDtoList = searchService.searchUsers(keyword);
        return new ResponseEntity<>(userDtoList, HttpStatus.OK);
    }




}