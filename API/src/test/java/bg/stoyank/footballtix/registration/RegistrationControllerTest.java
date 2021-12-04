package bg.stoyank.footballtix.registration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class RegistrationControllerTest {
    @InjectMocks
    private RegistrationController componentUnderTest;

    @Mock
    private RegistrationService registrationService;

    @Test
    @DisplayName("Ensure register() invokes proper service method.")
    void testRegister() {
        componentUnderTest.register(mock(RegistrationRequest.class));

        verify(registrationService).register(any());
    }

    @Test
    @DisplayName("Ensure sendConfirmationToken() invokes proper service method.")
    void testSendConfirmationToken() {
        componentUnderTest.sendConfirmationToken(mock(Map.class));

        verify(registrationService).sendConfirmationToken(any());
    }

    @Test
    @DisplayName("Ensure confirm() invokes proper service method.")
    void testConfirm() {
        componentUnderTest.confirm("token");

        verify(registrationService).confirmToken(any());
    }
}