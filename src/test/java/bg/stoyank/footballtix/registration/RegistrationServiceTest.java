package bg.stoyank.footballtix.registration;

import bg.stoyank.footballtix.registration.confirmationtoken.ConfirmationTokenService;
import bg.stoyank.footballtix.user.User;
import bg.stoyank.footballtix.user.UserService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RegistrationServiceTest {
    @InjectMocks
    private RegistrationService componentUnderTest;
    @Mock
    private UserService userService;
    @Mock
    private ConfirmationTokenService confirmationTokenService;

    @Test
    @Disabled
    @DisplayName("Ensure register() invokes createUser().")
    void testRegisterInvokesSaveUser() {
        RegistrationRequest stub = mock(RegistrationRequest.class);

        componentUnderTest.register(stub);

        verify(userService).createUser(any(User.class));
    }

}