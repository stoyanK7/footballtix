package bg.stoyank.footballtix;

import bg.stoyank.footballtix.model.User;
import bg.stoyank.footballtix.service.UserService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserServiceTests {
    private final UserService userService;
//    private final UserRepository userRepository;

    @Autowired
    public UserServiceTests(UserService userService) {
        this.userService = userService;
    }



    @Test
    @Order(1)
    public void testRegisterUser() {
        User user = new User();

        this.userService.registerUser(user);

        Assertions.assertEquals(1, this.userService.getAllUsers().size());
    }

}
