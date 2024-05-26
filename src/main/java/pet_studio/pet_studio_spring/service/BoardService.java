package pet_studio.pet_studio_spring.service;

import pet_studio.pet_studio_spring.domain.Board;
import pet_studio.pet_studio_spring.domain.User;
import pet_studio.pet_studio_spring.dto.board.BoardWriteRequestDto;

public interface BoardService {
    Long saveBoard(BoardWriteRequestDto boardWriteRequestDto, String userId);
}
