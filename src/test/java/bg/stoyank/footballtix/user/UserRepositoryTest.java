package bg.stoyank.footballtix.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {
    @Autowired
    private UserRepository componentUnderTest;

    @Test
    @DisplayName("Ensure the query for enabling a user works.")
    void enableUser() {
        User user = new User("full name", "test@mail.com", "passwd", UserRole.USER);
        System.out.println(user);
        componentUnderTest.save(user);
        componentUnderTest.enableUser("test@mail.com");
        User enabledUser = componentUnderTest.getByEmail("test@mail.com");
        System.out.println(enabledUser);
        assertThat(enabledUser.isEnabled()).isTrue();
    }
}