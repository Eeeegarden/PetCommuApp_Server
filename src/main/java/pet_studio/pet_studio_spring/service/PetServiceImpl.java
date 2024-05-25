package pet_studio.pet_studio_spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import pet_studio.pet_studio_spring.domain.Mypet;
import pet_studio.pet_studio_spring.repository.PetRepository;

@Service
public class PetServiceImpl implements PetService {
    @Autowired
    private final PetRepository petRepository;

    public PetServiceImpl(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public ResponseEntity<String> addPet(@RequestBody Mypet pet) {
        try {
            // 펫 정보 저장
            petRepository.save(pet);
            return ResponseEntity.ok("펫 정보가 성공적으로 저장되었습니다.");
        } catch (Exception e) {
            // 저장 실패 시 예외 처리
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("펫 정보 저장에 실패했습니다.");
        }
    }
}