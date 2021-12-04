package bg.stoyank.footballtix.footballmatch;

import bg.stoyank.footballtix.footballmatch.exception.FootballMatchNotFoundException;
import bg.stoyank.footballtix.task.TaskSchedulingService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class FootballMatchServiceTest {
    @InjectMocks
    private FootballMatchService componentUnderTest;

    @Mock
    private FootballMatchRepository footballMatchRepository;
    @Mock
    private TaskSchedulingService taskSchedulingService;

    @Test
    @DisplayName("Ensure the method findAll() is invoked.")
    void testGetAllFootballMatches() {
        componentUnderTest.getAllFootballMatches();
        verify(footballMatchRepository).findAll();
    }

    @Test
    @DisplayName("Ensure the football match id is being passed on to getById().")
    void testGetFootballMatchById() {
        long footballMatchId = 1;
        given(footballMatchRepository.existsById(footballMatchId))
                .willReturn(true);

        componentUnderTest.getFootballMatchById(footballMatchId);

        ArgumentCaptor<Long> footballMatchIdArgumentCaptor = ArgumentCaptor.forClass(long.class);

        verify(footballMatchRepository).getById(footballMatchIdArgumentCaptor.capture());

        Long capturedFootballMatchId = footballMatchIdArgumentCaptor.getValue();
        assertThat(capturedFootballMatchId).isEqualTo(footballMatchId);
    }

    @Test
    @DisplayName("Ensure FootballMatchNotFoundException is thrown in getFootballMatchById().")
    void testGetFootballMatchByIdFootballMatchNotFoundException() {
        long footballMatchId = 1;
        given(footballMatchRepository.existsById(footballMatchId))
                .willReturn(false);

        assertThatThrownBy(() -> componentUnderTest.getFootballMatchById(footballMatchId))
                .isInstanceOf(FootballMatchNotFoundException.class);
    }

    @Test
    @DisplayName("Ensure the football match is being passed on to save() from createFootballMatch().")
    void testCreateFootballMatch() {
        FootballMatch footballMatch = mock(FootballMatch.class, RETURNS_DEEP_STUBS);

        componentUnderTest.createFootballMatch(footballMatch);

        ArgumentCaptor<FootballMatch> footballMatchArgumentCaptor = ArgumentCaptor.forClass(FootballMatch.class);

        verify(footballMatchRepository).save(footballMatchArgumentCaptor.capture());
        verify(footballMatch, atLeast(1)).getId();

        FootballMatch capturedFootballMatch = footballMatchArgumentCaptor.getValue();
        assertThat(capturedFootballMatch).isEqualTo(footballMatch);
    }

    @Test
    @DisplayName("Ensure the football match id is being passed on to deleteById() from deleteFootballMatchById().")
    void testDeleteFootballMatch() {
        long footballMatchId = 1;
        given(footballMatchRepository.existsById(footballMatchId))
                .willReturn(true);

        componentUnderTest.deleteFootballMatchById(footballMatchId);

        ArgumentCaptor<Long> footballMatchIdArgumentCaptor = ArgumentCaptor.forClass(long.class);

        verify(footballMatchRepository).deleteById(footballMatchIdArgumentCaptor.capture());

        Long capturedFootballMatchId = footballMatchIdArgumentCaptor.getValue();
        assertThat(capturedFootballMatchId).isEqualTo(footballMatchId);
    }

    @Test
    @DisplayName("Ensure FootballMatchNotFoundException is thrown in deleteFootballMatchById().")
    void testDeleteFootballMatchByIdFootballMatchNotFoundException() {
        long footballMatchId = 1;
        given(footballMatchRepository.existsById(footballMatchId))
                .willReturn(false);

        assertThatThrownBy(() -> componentUnderTest.deleteFootballMatchById(footballMatchId))
                .isInstanceOf(FootballMatchNotFoundException.class);
    }

    @Test
    @DisplayName("Ensure getAllUpcomingFootballMatches invokes the correct repository method.")
    void testGetAllUpcomingFootballMatches() {
        componentUnderTest.getAllUpcomingFootballMatches();
        verify(footballMatchRepository).getFootballMatchesByStartingDateTimeAfterOrderByStartingDateTimeAsc(any());
    }

    @Test
    @DisplayName("Ensure subtractTicketsAvailableByOne() works.")
    void testSubtractTicketsAvailableByOne() {
        FootballMatch footballMatch = mock(FootballMatch.class, RETURNS_DEEP_STUBS);
        given(footballMatchRepository.existsById(any()))
                .willReturn(true);
        given(footballMatchRepository.getById(any()))
                .willReturn(footballMatch);

        componentUnderTest.subtractTicketsAvailableByOne(footballMatch.getId());

        verify(footballMatch).setTicketsAvailable(anyInt());
        verify(footballMatchRepository).save(footballMatch);
    }

    @Test
    @DisplayName("Ensure updateFootballMatch() invokes proper methods.")
    void testUpdateFootballMatch() {
        FootballMatch footballMatch = mock(FootballMatch.class, RETURNS_DEEP_STUBS);

        given(footballMatchRepository.getById(any()))
                .willReturn(footballMatch);

        componentUnderTest.updateFootballMatch(1, new FootballMatch());

        verify(footballMatch).setHomeTeam(any());
        verify(footballMatch).setAwayTeam(any());
        verify(footballMatch).setStartingDateTime(any());
        verify(footballMatch).setStadium(any());
        verify(footballMatch).setLocation(any());
        verify(footballMatch).setLeague(any());
        verify(footballMatchRepository).save(footballMatch);
    }

    @Test
    @DisplayName("Ensure getAllPastFootballMatches() invokes proper repository method.")
    void testGetAllPastFootballMatches() {
        componentUnderTest.getAllPastFootballMatches();

        verify(footballMatchRepository).getFootballMatchesByStartingDateTimeBeforeOrderByStartingDateTimeDesc(any(Date.class));
    }

    @Test
    @DisplayName("Ensure scheduleRemindersAfterStartup() works.")
    void testScheduleRemindersAfterStartup() {
        given(footballMatchRepository.getFootballMatchesByStartingDateTimeAfterOrderByStartingDateTimeAsc(any(Date.class)))
                .willReturn(Arrays.asList(
                        mock(FootballMatch.class, RETURNS_DEEP_STUBS),
                        mock(FootballMatch.class, RETURNS_DEEP_STUBS)
                ));

        componentUnderTest.scheduleRemindersAfterStartup();

        verify(taskSchedulingService, atLeast(1)).scheduleATask(any());
    }
}