package pet_studio.pet_studio_spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "pet_studio.pet_studio_spring")
@EnableAutoConfiguration
public class PetStudioSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetStudioSpringApplication.class, args);
	}

}
