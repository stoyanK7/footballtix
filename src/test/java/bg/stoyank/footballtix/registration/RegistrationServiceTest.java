package bg.stoyank.footballtix.registration;

import bg.stoyank.footballtix.email.EmailService;
import bg.stoyank.footballtix.email.EmailTemplateService;
import bg.stoyank.footballtix.registration.confirmationtoken.ConfirmationToken;
import bg.stoyank.footballtix.registration.confirmationtoken.ConfirmationTokenService;
import bg.stoyank.footballtix.user.User;
import bg.stoyank.footballtix.user.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
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
    @Mock
    private EmailService emailService;
    @Mock
    private EmailTemplateService emailTemplateService;

    @Test
    @DisplayName("Ensure register() creates user and sends confirmation token.")
    void testRegisterInvokesSaveUser() {
        given(userService.getUserByEmail(any()))
                .willReturn(mock(User.class, RETURNS_DEEP_STUBS));

        componentUnderTest.register(mock(RegistrationRequest.class));

        verify(userService).createUser(any(User.class));
    }

    @Test
    @DisplayName("Ensure sendConfirmationToken() invokes proper services.")
    void testSendConfirmationToken() {
        String email = "test@mail.com";

        given(userService.getUserByEmail(any()))
                .willReturn(mock(User.class));

        componentUnderTest.sendConfirmationToken(email);

        verify(confirmationTokenService).createConfirmationToken(any());
        verify(emailService).send(any(), any(), any());
        verify(emailTemplateService).buildRegistrationConfirmationEmail(any(), any());
    }

    @Test
    @DisplayName("Ensure confirmToken() invokes proper services.")
    void testConfirmToken() {
//        ConfirmationToken confirmationToken
//                = new ConfirmationToken("token",
//                LocalDateTime.now(),
//                LocalDateTime.now(),
//                new User());

        ConfirmationToken confirmationToken
                = mock(ConfirmationToken.class, RETURNS_DEEP_STUBS);

        given(confirmationTokenService.getConfirmationToken(any()))
                .willReturn(confirmationToken);
        confirmationToken.setExpiresAt(LocalDateTime.now());
        given(confirmationToken.getExpiresAt().isBefore(any()))
                .willReturn(false);

        componentUnderTest.confirmToken("token");

        verify(userService).enableUser(any());
        verify(confirmationTokenService).deleteToken(any());
    }
}