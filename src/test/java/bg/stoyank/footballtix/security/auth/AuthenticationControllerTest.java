package bg.stoyank.footballtix.security.auth;

import bg.stoyank.footballtix.jwt.JwtService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Map;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AuthenticationControllerTest {
    @InjectMocks
    private AuthenticationController componentUnderTest;

    @Mock
    private JwtService jwtService;
    @Mock
    private UserDetailsService userService;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private AuthenticationService authenticationService;

    @Test
    @DisplayName("Ensure createAuthenticationToken() invokes the service.")
    void testCreateAuthenticationToken() {
        AuthenticationRequest authenticationRequest
                = new AuthenticationRequest("test", "testpw");

        componentUnderTest.createAuthenticationToken(authenticationRequest);

        verify(authenticationManager).authenticate(any());
        verify(userService).loadUserByUsername(any());
        verify(jwtService).generateJwtToken(any());
    }

    @Test
    @DisplayName("Ensure forgotPassword() invokes the service.")
    void testForgotPassword() {
        componentUnderTest.forgotPassword(mock(Map.class));

        verify(authenticationService).sendRecoveryEmail(any());
    }

    @Test
    @DisplayName("Ensure confirm() invokes the service.")
    void testConfirm() {
        componentUnderTest.confirm(UUID.randomUUID().toString(), mock(Map.class));

        verify(authenticationService).resetPassword(any(), any(), any());
    }
}