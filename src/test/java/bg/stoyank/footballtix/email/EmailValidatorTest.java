package bg.stoyank.footballtix.email;

import bg.stoyank.footballtix.email.EmailValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class EmailValidatorTest {
    private final EmailValidator componentUnderTest = new EmailValidator();

    @Test
    @DisplayName("Ensure valid emails pass through test().")
    void testTestValidEmail() {
        String email = "stoyank_123@gmail.com";

        assertThat(componentUnderTest.test(email)).isTrue();
    }

    @Test
    @DisplayName("Ensure invalid emails without TLD do not pass through test().")
    void testTestInvalidEmailNoTLD() {
        String email = "stoyank_123@gmail.";

        assertThat(componentUnderTest.test(email)).isFalse();
    }

    @Test
    @DisplayName("Ensure invalid emails without @ do not pass through test().")
    void testTestInvalidEmailNOAt() {
        String email = "stoyank_123gmail.com";

        assertThat(componentUnderTest.test(email)).isFalse();
    }

    @Test
    @DisplayName("Ensure invalid emails without name do not pass through test().")
    void testTestInvalidEmailNoName() {
        String email = "@gmail.com";

        assertThat(componentUnderTest.test(email)).isFalse();
    }
}