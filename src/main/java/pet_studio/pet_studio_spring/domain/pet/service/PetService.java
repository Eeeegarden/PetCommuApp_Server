package pet_studio.pet_studio_spring.domain.pet.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import pet_studio.pet_studio_spring.domain.pet.entity.Mypet;

public interface PetService {

    public ResponseEntity<String> addPet(@RequestBody Mypet pet);

}
