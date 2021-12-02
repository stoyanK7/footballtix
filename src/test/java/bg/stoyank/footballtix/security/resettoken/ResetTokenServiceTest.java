package bg.stoyank.footballtix.security.resettoken;

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
class ResetTokenServiceTest {
    @InjectMocks
    private ResetTokenService componentUnderTest;

    @Mock
    private ResetTokenRepository resetTokenRepository;

    @Test
    @DisplayName("Ensure saveResetToken() invokes repository method.")
    void testSaveResetToken() {
        componentUnderTest.saveResetToken(mock(ResetToken.class, RETURNS_DEEP_STUBS));
        verify(resetTokenRepository).save(any());
    }

    @Test
    @DisplayName("Ensure getResetToken() invokes repository method.")
    void testGetResetToken() {
        given(resetTokenRepository.existsByToken(any()))
                .willReturn(true);

        componentUnderTest.getResetToken("");

        verify(resetTokenRepository).getByToken("");
    }

    @Test
    @DisplayName("Ensure getResetToken() throws ResetTokenNotFoundException.")
    void testGetResetTokenThrowsTokenNotFoundException() {
        given(resetTokenRepository.existsByToken(any()))
                .willReturn(false);

        assertThatThrownBy(() -> componentUnderTest.getResetToken(any()))
                .isInstanceOf(ResetTokenNotFoundException.class);
    }
}