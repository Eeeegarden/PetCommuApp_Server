package pet_studio.pet_studio_spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pet_studio.pet_studio_spring.domain.Mypet;
import pet_studio.pet_studio_spring.service.PetService;

@RestController
@RequestMapping("/pet")
public class PetController {
    @Autowired
    private final PetService petService;

    @Autowired
    public PetController(PetService petService) {
        this.petService = petService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addPet(@RequestBody Mypet pet) {
        try {
            ResponseEntity<String> response = petService.addPet(pet);
            return response;
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("펫 정보 저장에 실패했습니다.");
        }
    }
    }
