package pet_studio.pet_studio_spring.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pet_studio.pet_studio_spring.domain.Board;
import pet_studio.pet_studio_spring.domain.Image;
import pet_studio.pet_studio_spring.domain.User;
import pet_studio.pet_studio_spring.dto.board.*;
import pet_studio.pet_studio_spring.repository.BoardRepository;
import pet_studio.pet_studio_spring.repository.ImageRepository;
import pet_studio.pet_studio_spring.repository.UserRepository;
import pet_studio.pet_studio_spring.service.BoardService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

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
            @RequestPart("userId") String userId,
            @RequestPart("file") MultipartFile file,
            @RequestPart("content") String content) {
        BoardWriteRequestDto boardWriteRequestDto = new BoardWriteRequestDto();
        boardWriteRequestDto.setFile(file);
        boardWriteRequestDto.setContent(content);

        try {
            boardService.saveBoard(boardWriteRequestDto, userId);
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
            @RequestParam("userId") String userId
    ) {
        BoardDto board = boardService.updateBoard(boardId, boardUpdateDto, userId);
        return ResponseEntity.ok(board);
    }

    // 게시글 삭제
    @DeleteMapping("/{boardId}")
    public void deleteBoard(@PathVariable Long boardId, String userId) {
        boardService.deleteBoard(boardId, userId);
    }


    // 게시글 목록
    @GetMapping
    public ResponseEntity<Page<BoardListDto>> getBoardList(String userId, Pageable pageable) {

        Page<BoardListDto> boards = boardService.getAllBoards(userId, pageable);

        return ResponseEntity.ok(boards);
    }

    // 내가 쓴 글
    @GetMapping("/myboard")
    public ResponseEntity<Page<BoardListDto>> getMyBoardList(String userId, Pageable pageable) {

        Page<BoardListDto> boards = boardService.getMyBoards(userId, pageable);

        return ResponseEntity.ok(boards);
    }

    // 게시글 상세
    @GetMapping("/{boardId}")
    public ResponseEntity<BoardDetailDto> getBoardDetail(@PathVariable Long boardId,
                                                        @RequestParam String userId) {
        BoardDetailDto board = boardService.getBoardById(boardId, userId);
        return ResponseEntity.ok(board);
    }
    
    
    // 좋아요
    @PostMapping("/{boardId}/like")
    public void toggleLikeBoard(@PathVariable Long boardId, String userId) {
        boardService.toggleLikeBoard(boardId, userId);
    }




}