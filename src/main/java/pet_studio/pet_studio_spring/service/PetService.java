package pet_studio.pet_studio_spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import pet_studio.pet_studio_spring.domain.Mypet;
import pet_studio.pet_studio_spring.repository.PetRepository;

public interface PetService {

    public ResponseEntity<String> addPet(@RequestBody Mypet pet);

}
