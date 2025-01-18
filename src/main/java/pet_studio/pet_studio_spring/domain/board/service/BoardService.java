package pet_studio.pet_studio_spring.domain.board.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pet_studio.pet_studio_spring.domain.board.dto.*;

public interface BoardService {
    Long saveBoard(BoardWriteRequestDto boardWriteRequestDto, String email);
    public BoardDto updateBoard(Long boardId, BoardUpdateDto boardUpdateDto, String email);
    public void deleteBoard(Long boardId, String email);

    public Page<BoardListDto> getAllBoards(String email, Pageable pageable);
    public Page<BoardListDto> getMyBoards(String email, Pageable pageable);
    public BoardDetailDto getBoardById(Long boardId, String email);
    public void toggleLikeBoard(Long boardId, String email);
}
