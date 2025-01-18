package pet_studio.pet_studio_spring.domain.board.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pet_studio.pet_studio_spring.domain.board.dto.*;
import pet_studio.pet_studio_spring.domain.board.entity.Board;
import pet_studio.pet_studio_spring.domain.board.repositoy.BoardRepository;
import pet_studio.pet_studio_spring.domain.board.service.BoardService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardRepository boardRepository;
    private final BoardService boardService;
    private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

    //
    @GetMapping("/get-all")
    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }

    // 게시글 업로드
    @PostMapping("/upload")
    public ResponseEntity<String> uploadBoard(
            @RequestPart("email") String email,
            @RequestPart("file") MultipartFile file,
            @RequestPart("content") String content) {
        BoardWriteRequestDto boardWriteRequestDto = new BoardWriteRequestDto();
        boardWriteRequestDto.setFile(file);
        boardWriteRequestDto.setContent(content);

        try {
            boardService.saveBoard(boardWriteRequestDto, email);
            return ResponseEntity.ok().body("게시글 업로드에 성공했습니다.");
        } catch (Exception e) {
            logger.error("Error uploading board", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("게시글 업로드에 실패했습니다.");
        }
    }

    // 게시글 수정
    @PutMapping("/{boardId}")
    public ResponseEntity<BoardDto> updateBoard(
            @PathVariable Long boardId,
            @RequestPart("boardUpdateDto") BoardUpdateDto boardUpdateDto,
            @RequestParam("email") String email
    ) {
        BoardDto board = boardService.updateBoard(boardId, boardUpdateDto, email);
        return ResponseEntity.ok(board);
    }

    // 게시글 삭제
    @DeleteMapping("/{boardId}")
    public void deleteBoard(@PathVariable Long boardId, String email) {
        boardService.deleteBoard(boardId, email);
    }


    // 게시글 목록
    @GetMapping
    public ResponseEntity<Page<BoardListDto>> getBoardList(String email, Pageable pageable) {

        Page<BoardListDto> boards = boardService.getAllBoards(email, pageable);

        return ResponseEntity.ok(boards);
    }

    // 내가 쓴 글
    @GetMapping("/myboard")
    public ResponseEntity<Page<BoardListDto>> getMyBoardList(String email, Pageable pageable) {

        Page<BoardListDto> boards = boardService.getMyBoards(email, pageable);

        return ResponseEntity.ok(boards);
    }

    // 게시글 상세
    @GetMapping("/{boardId}")
    public ResponseEntity<BoardDetailDto> getBoardDetail(@PathVariable Long boardId,
                                                         @RequestParam String email) {
        BoardDetailDto board = boardService.getBoardById(boardId, email);
        return ResponseEntity.ok(board);
    }
    
    
    // 좋아요
    @PostMapping("/{boardId}/like")
    public void toggleLikeBoard(@PathVariable Long boardId, String email) {
        boardService.toggleLikeBoard(boardId, email);
    }




}