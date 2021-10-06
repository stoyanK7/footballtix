package bg.stoyank.footballtix.registration;

import bg.stoyank.footballtix.email.EmailSender;
import bg.stoyank.footballtix.email.EmailValidator;
import bg.stoyank.footballtix.email.exception.InvalidEmailException;
import bg.stoyank.footballtix.registration.token.ConfirmationTokenService;
import bg.stoyank.footballtix.registration.token.exception.EmailConfirmedException;
import bg.stoyank.footballtix.user.User;
import bg.stoyank.footballtix.user.UserService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RegistrationServiceTest {
    @InjectMocks
    private RegistrationService componentUnderTest;

    @Mock
    private UserService userService;
    @Mock
    private EmailSender emailSender;
    @Mock
    private EmailValidator emailValidator;
    @Mock
    private ConfirmationTokenService confirmationTokenService;

    @Test
    @DisplayName("Ensure register() invokes createUser().")
    void testRegisterInvokesSaveUser() {
        RegistrationRequest stub = mock(RegistrationRequest.class);
        given(emailValidator.test(stub.getEmail())).willReturn(true);

        componentUnderTest.register(stub);

        verify(userService).createUser(any(User.class));
    }

    @Test
    @DisplayName("Ensure InvalidEmailException is thrown in register().")
    void testRegisterInvalidEmailException() {
        given(emailValidator.test(any()))
                .willReturn(false);

        assertThatThrownBy(() -> componentUnderTest.register(mock(RegistrationRequest.class)))
                .isInstanceOf(InvalidEmailException.class);
    }

    @Test
    @Disabled
    @DisplayName("Ensure EmailConfirmedException is thrown in confirmToken().")
    void testConfirmTokenEmailConfirmedException() {
        given(confirmationTokenService.getConfirmationToken(anyString()).getConfirmedAt())
                .willReturn(null);

        assertThatThrownBy(() -> componentUnderTest.confirmToken(anyString()))
                .isInstanceOf(EmailConfirmedException.class);
    }
}