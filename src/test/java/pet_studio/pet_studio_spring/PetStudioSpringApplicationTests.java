package pet_studio.pet_studio_spring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pet_studio.pet_studio_spring.data.dto.UserDTO;
import pet_studio.pet_studio_spring.data.dto.UserDao;

import java.util.List;

@SpringBootTest
class PetStudioSpringApplicationTests {

	@Autowired
	private UserDao userDao;

	//@Test
//	void addUserTest() {
//		UserDTO user = new UserDTO();
//		user.setUserId(2);
//		user.setUserPassword("4567");
//		user.setNickName("홍길동");
//		userDao.save(user);
//	}



}
