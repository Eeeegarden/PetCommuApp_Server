//package pet_studio.pet_studio_spring.service;
//
//import lombok.RequiredArgsConstructor;
//import lombok.Value;
//import org.mariadb.jdbc.internal.logging.LoggerFactory;
//import org.springframework.stereotype.Service;
//import pet_studio.pet_studio_spring.repository.ImageRepository;
//import pet_studio.pet_studio_spring.repository.UserRepository;
//
//import java.util.logging.Logger;
//
//@Service
//@RequiredArgsConstructor
//public class ImageServiceImpl implements ImageService {
//    private static final Logger logger = LoggerFactory.getLogger(ImageServiceImpl.class);
//
//    private final ImageRepository imageRepository;
//    private final UserRepository userRepository;
//
//    @Value("${file.path}")
//    private String uploadFolder;
//
//    @Override
//    public void upload(ImageUploadDTO imageUploadDTO, String email) {
//        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("이메일이 존재하지 않습니다."));
//        MultipartFile file = imageUploadDTO.getFile();
//
//        UUID uuid = UUID.randomUUID();
//        String imageFileName = uuid + "_" + file.getOriginalFilename();
//
//        File destinationFile = new File(uploadFolder + imageFileName);
//
//        try {
//            file.transferTo(destinationFile);
//
//            Image image = imageRepository.findByMember(member);
//            if (image != null) {
//                // 이미지가 이미 존재하면 url 업데이트
//                image.updateUrl("/profileImages/" + imageFileName);
//            } else {
//                // 이미지가 없으면 객체 생성 후 저장
//                image = Image.builder()
//                        .member(member)
//                        .url("/profileImages/" + imageFileName)
//                        .build();
//            }
//            imageRepository.save(image);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Override
//    public ImageResponseDTO findImage(String email) {
//        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("이메일이 존재하지 않습니다."));
//        Image image = imageRepository.findByMember(member);
//
//        String defaultImageUrl = "/profileImages/anonymous.png";
//
//        if (image == null) {
//            return ImageResponseDTO.builder()
//                    .url(defaultImageUrl)
//                    .build();
//        } else {
//            return ImageResponseDTO.builder()
//                    .url(image.getUrl())
//                    .build();
//        }
//    }
//}
//}
