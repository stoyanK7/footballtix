package bg.stoyank.footballtix.security.auth;

import bg.stoyank.footballtix.email.EmailService;
import bg.stoyank.footballtix.email.EmailTemplateService;
import bg.stoyank.footballtix.security.resettoken.ResetToken;
import bg.stoyank.footballtix.security.resettoken.ResetTokenService;
import bg.stoyank.footballtix.user.UserService;
import bg.stoyank.footballtix.user.exception.PasswordsDoNotMatchException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {
    @InjectMocks
    private AuthenticationService componentUnderTest;

    @Mock
    private EmailService emailService;
    @Mock
    private ResetTokenService resetTokenService;
    @Mock
    private UserService userService;
    @Mock
    private EmailTemplateService emailTemplateService;

    @Test
    @DisplayName("Ensure sendRecoveryEmail() invokes other services.")
    void testSendRecoveryEmail() {
        componentUnderTest.sendRecoveryEmail("test@mail.com");

        verify(userService).getUserByEmail(any());
        verify(resetTokenService).saveResetToken(any(ResetToken.class));
        verify(emailTemplateService).buildPasswordRecoveryEmail(any(), any());
        verify(emailService).send(any(), any(), any());
    }

    @Test
    @DisplayName("Ensure resetPassword() throws PasswordDoNotMatchException.")
    void testResetPasswordThrowsPasswordDoNotMatchException() {
        assertThatThrownBy(() -> componentUnderTest
                .resetPassword("", "pw1", "pw2"))
                .isInstanceOf(PasswordsDoNotMatchException.class);
    }

    @Test
    @DisplayName("Ensure resetPassword() updates password.")
    void testResetPassword() {
        given(resetTokenService.getResetToken(any()))
                .willReturn(mock(ResetToken.class, RETURNS_DEEP_STUBS));
        given(resetTokenService.getResetToken(any()).getEmail())
                .willReturn("test@mail.com");

        componentUnderTest.resetPassword("", "pw1", "pw1");

        verify(userService).updatePassword(any(), any(), any());
    }
}