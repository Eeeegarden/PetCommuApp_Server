package pet_studio.pet_studio_spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pet_studio.pet_studio_spring.service.UserServiceImpl;

@SpringBootTest
class PetStudioSpringApplicationTests {

	@Autowired
	private UserServiceImpl userServiceImpl;

	//@Test
//	void addUserTest() {
//		UserDTO user = new UserDTO();
//		user.setUserId(2);
//		user.setUserPassword("4567");
//		user.setNickName("홍길동");
//		userDao.save(user);
//	}



}
