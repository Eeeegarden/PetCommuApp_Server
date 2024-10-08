package pet_studio.pet_studio_spring.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pet_studio.pet_studio_spring.domain.Board;
import pet_studio.pet_studio_spring.domain.User;
import pet_studio.pet_studio_spring.dto.board.*;

public interface BoardService {
    Long saveBoard(BoardWriteRequestDto boardWriteRequestDto, String userId);
    public BoardDto updateBoard(Long boardId, BoardUpdateDto boardUpdateDto, String userId);
    public void deleteBoard(Long boardId, String userId);

    public Page<BoardListDto> getAllBoards(String userId, Pageable pageable);
    public Page<BoardListDto> getMyBoards(String userId, Pageable pageable);
    public BoardDetailDto getBoardById(Long boardId, String userId);
    public void toggleLikeBoard(Long boardId, String userId);
}
