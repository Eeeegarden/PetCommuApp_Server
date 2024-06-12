package pet_studio.pet_studio_spring.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import pet_studio.pet_studio_spring.domain.Board;
import pet_studio.pet_studio_spring.domain.Image;
import pet_studio.pet_studio_spring.domain.Likes;
import pet_studio.pet_studio_spring.domain.User;
import pet_studio.pet_studio_spring.dto.board.BoardDetailDto;
import pet_studio.pet_studio_spring.dto.board.BoardListDto;
import pet_studio.pet_studio_spring.dto.board.BoardWriteRequestDto;
import pet_studio.pet_studio_spring.exception.CustomException;
import pet_studio.pet_studio_spring.repository.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static pet_studio.pet_studio_spring.domain.FollowStatus.FOLLOWING;
import static pet_studio.pet_studio_spring.exception.ErrorCode.*;


@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final ImageRepository imageRepository;
    private final FollowRepository followRepository;
    private final LikesRepository likesRepository;
    private static final Logger logger = LoggerFactory.getLogger(BoardService.class);
    @Value("${spring.file.boardImagePath}")
    private String uploadFolder;

    @Override
    public Long saveBoard(BoardWriteRequestDto boardWriteRequestDto, String userId) {
        User user = userRepository.findByUserId(userId).orElseThrow(() -> new UsernameNotFoundException("아이디가 존재하지 않습니다."));

        MultipartFile file = boardWriteRequestDto.getFile();

        UUID uuid = UUID.randomUUID();
        String imageFileName = uuid + "_" + file.getOriginalFilename();

        File destinationFile = new File(uploadFolder + imageFileName);

        try {
            file.transferTo(destinationFile);

            String imageUrl = "/boardImages/" + imageFileName;

            Image image = Image.builder()
                    .url(imageUrl)
                    .user(user)
                    .type("board")
                    .build();
            imageRepository.save(image);

            Board board = Board.builder()
                    .content(boardWriteRequestDto.getContent())
                    .user(user)
                    .image(image)
                    .likeCount(0)
                    .build();
            boardRepository.save(board);


            return board.getId();
        } catch (IOException e) {
            logger.error("Error saving board image", e);
            throw new RuntimeException(e);
        } catch (Exception e) {
            logger.error("Error saving board", e);
            throw e;
        }
    }

    // 게시글 목록
    public Page<BoardListDto> getAllBoards(String userId, Pageable pageable){
        User currentUser = userRepository.findByUserId(userId)
                .orElseThrow(() -> new CustomException(NOT_FOUND_USER));

        List<User> followings = followRepository.findFollowingUsersByStatusAndFollower(FOLLOWING,
                currentUser);

        // 피드 테스트용 (팔로잉에 나 추가해서 팔로잉+내가 쓴글 조회)
        followings.add(currentUser);

        Pageable sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
                Sort.by(Sort.Direction.DESC, "createdAt"));

        Page<Board> boardPage = boardRepository.findByUserIn(followings, sortedPageable);

        return BoardListDto.convertToDTO(boardPage, userId);
    }


    // 좋아요
    @Transactional
    public void toggleLikeBoard(Long boardId, String userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new CustomException(NOT_FOUND_USER));

        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new CustomException(NOT_FOUND_POST));

        Likes existingLike = likesRepository.findByUserAndBoard(user, board);

        if (existingLike != null) {
            likesRepository.delete(existingLike);
        } else {
            Likes like = Likes.builder()
                    .user(user)
                    .board(board)
                    .isLike(true)
                    .build();

            likesRepository.save(like);
        }
    }
    
    //게시글 상세
    public BoardDetailDto getBoardById(Long boardId, String userId) {
        User currentUser = userRepository.findByUserId(userId)
                .orElseThrow(() -> new CustomException(NOT_FOUND_USER));

        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new CustomException(NOT_FOUND_POST));

        if (board.getUser().equals(currentUser) || !board.getUser().getIsPrivate()) {
            return BoardDetailDto.convertToDTO(board);
        }

        boolean isFollowing = followRepository
                .existsByStatusAndFollowerAndFollowing(FOLLOWING, currentUser, board.getUser());

        if (isFollowing) {
            return BoardDetailDto.convertToDTO(board);
        }

        throw new CustomException(UNAUTHORIZED_ACCESS);
    }


    private static class UsernameNotFoundException extends RuntimeException {
        public UsernameNotFoundException(String message) {
            super(message);
        }
    }
}