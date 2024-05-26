package pet_studio.pet_studio_spring.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pet_studio.pet_studio_spring.domain.Board;
import pet_studio.pet_studio_spring.domain.Image;
import pet_studio.pet_studio_spring.domain.User;
import pet_studio.pet_studio_spring.dto.board.BoardWriteRequestDto;
import pet_studio.pet_studio_spring.repository.BoardRepository;
import pet_studio.pet_studio_spring.repository.ImageRepository;
import pet_studio.pet_studio_spring.repository.UserRepository;

import java.io.File;
import java.io.IOException;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final ImageRepository imageRepository;
    private static final Logger logger = LoggerFactory.getLogger(BoardService.class);
    @Value("${spring.file.boardImagePath}")
    private String uploadFolder;

    @Override
    public Long saveBoard(BoardWriteRequestDto boardWriteRequestDto, String userId) {
        User user = userRepository.findByUserId(userId).orElseThrow(() -> new UsernameNotFoundException("이메일이 존재하지 않습니다."));

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
                    .favorite_count(0)
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

    private static class UsernameNotFoundException extends RuntimeException {
        public UsernameNotFoundException(String message) {
            super(message);
        }
    }
}