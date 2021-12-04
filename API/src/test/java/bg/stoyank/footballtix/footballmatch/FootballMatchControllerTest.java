package bg.stoyank.footballtix.footballmatch;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class FootballMatchControllerTest {
    @InjectMocks
    private FootballMatchController componentUnderTest;

    @Mock
    private FootballMatchService footballMatchService;

    @Test
    @DisplayName("Ensure getAllFootballMatches() invokes the proper service method.")
    void testGetAllFootballMatches() {
        componentUnderTest.getAllFootballMatches();

        verify(footballMatchService).getAllFootballMatches();
    }

    @Test
    @DisplayName("Ensure getAllUpcomingFootballMatches() invokes the proper service method.")
    void testGetAllUpcomingFootballMatches() {
        componentUnderTest.getAllUpcomingFootballMatches();

        verify(footballMatchService).getAllUpcomingFootballMatches();
    }

    @Test
    @DisplayName("Ensure getAllPastFootballMatches() invokes the proper service method.")
    void testGetAllPastFootballMatches() {
        componentUnderTest.getAllPastFootballMatches();

        verify(footballMatchService).getAllPastFootballMatches();
    }

    @Test
    @DisplayName("Ensure getFootballMatch() invokes the proper service method and passes the value.")
    void testGetFootballMatch() {
        int footballMatchId = 1;

        componentUnderTest.getFootballMatch(1);

        ArgumentCaptor<Long> footballMatchIdArgumentCaptor
                = ArgumentCaptor.forClass(Long.class);

        verify(footballMatchService)
                .getFootballMatchById(footballMatchIdArgumentCaptor.capture());

        long capturedFootballMatchId = footballMatchIdArgumentCaptor.getValue();
        assertThat(capturedFootballMatchId).isEqualTo(footballMatchId);
    }

    @Test
    @DisplayName("Ensure createFootballMatch() invokes the proper service method and passes the value.")
    void testCreateFootballMatch() {
        FootballMatch footballMatch = mock(FootballMatch.class, RETURNS_DEEP_STUBS);

        componentUnderTest.createFootballMatch(footballMatch);

        ArgumentCaptor<FootballMatch> footballMatchArgumentCaptor
                = ArgumentCaptor.forClass(FootballMatch.class);

        verify(footballMatchService)
                .createFootballMatch(footballMatchArgumentCaptor.capture());

        FootballMatch capturedFootballMatch = footballMatchArgumentCaptor.getValue();
        assertThat(capturedFootballMatch).isEqualTo(footballMatch);

        verify(footballMatchService).createFootballMatch(footballMatch);
    }

    @Test
    @DisplayName("Ensure updateFootballMatch() invokes the proper service method and passes the value.")
    void testUpdateFootballMatch() {
        long footballMatchId = 1;
        FootballMatch footballMatch = mock(FootballMatch.class, RETURNS_DEEP_STUBS);

        componentUnderTest.updateFootballMatch(footballMatchId, footballMatch);

        ArgumentCaptor<FootballMatch> footballMatchArgumentCaptor
                = ArgumentCaptor.forClass(FootballMatch.class);
        ArgumentCaptor<Long> footballMatchIdArgumentCaptor
                = ArgumentCaptor.forClass(Long.class);

        verify(footballMatchService)
                .updateFootballMatch(footballMatchIdArgumentCaptor.capture(),
                        footballMatchArgumentCaptor.capture());

        FootballMatch capturedFootballMatch = footballMatchArgumentCaptor.getValue();
        Long capturedFootballMatchId = footballMatchIdArgumentCaptor.getValue();
        assertThat(capturedFootballMatch).isEqualTo(footballMatch);
        assertThat(capturedFootballMatchId).isEqualTo(footballMatchId);
    }

    @Test
    @DisplayName("Ensure deleteFootballMatch() invokes the proper service method and passes the value.")
    void deleteFootballMatch() {
        long footballMatchId = 1;

        componentUnderTest.deleteFootballMatch(footballMatchId);

        ArgumentCaptor<Long> footballMatchIdArgumentCaptor
                = ArgumentCaptor.forClass(Long.class);

        verify(footballMatchService)
                .deleteFootballMatchById(footballMatchIdArgumentCaptor.capture());

        Long capturedFootballMatchId = footballMatchIdArgumentCaptor.getValue();
        assertThat(capturedFootballMatchId).isEqualTo(footballMatchId);

    }
}