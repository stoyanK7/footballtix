package bg.stoyank.footballtix.footballmatch;

import bg.stoyank.footballtix.footballmatch.exception.FootballMatchNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class FootballMatchServiceTest {
    @InjectMocks
    private FootballMatchService componentUnderTest;

    @Mock
    private FootballMatchRepository footballMatchRepository;

    @Test
    @DisplayName("Ensure the method findAll() is invoked.")
    void testGetAllFootballMatches() {
        // when
        componentUnderTest.getAllFootballMatches();
        // then
        verify(footballMatchRepository).findAll();
    }

    @Test
    @DisplayName("Ensure the football match id is being passed on to getById().")
    void testGetFootballMatchById() {
        // given
        int footballMatchId = 1;
        given(footballMatchRepository.existsById(footballMatchId))
                .willReturn(true);
        // when
        componentUnderTest.getFootballMatchById(footballMatchId);
        // then
        ArgumentCaptor<Integer> footballMatchIdArgumentCaptor = ArgumentCaptor.forClass(int.class);

        verify(footballMatchRepository).getById(footballMatchIdArgumentCaptor.capture());

        int capturedFootballMatchId = footballMatchIdArgumentCaptor.getValue();
        assertThat(capturedFootballMatchId).isEqualTo(footballMatchId);
    }

    @Test
    @DisplayName("Ensure FootballMatchNotFoundException is thrown in getFootballMatchById().")
    void testGetFootballMatchByIdFootballMatchNotFoundException() {
        // given
        int footballMatchId = 1;
        given(footballMatchRepository.existsById(footballMatchId))
                .willReturn(false);
        // then
        assertThatThrownBy(() -> componentUnderTest.getFootballMatchById(footballMatchId))
                .isInstanceOf(FootballMatchNotFoundException.class);
    }

    @Test
    @DisplayName("Ensure the football match is being passed on to save() from createFootballMatch().")
    void testCreateFootballMatch() {
        // given
        FootballMatch stub = mock(FootballMatch.class);
        // when
        componentUnderTest.createFootballMatch(stub);
        // then
        ArgumentCaptor<FootballMatch> footballMatchArgumentCaptor = ArgumentCaptor.forClass(FootballMatch.class);

        verify(footballMatchRepository).save(footballMatchArgumentCaptor.capture());

        FootballMatch capturedFootballMatch = footballMatchArgumentCaptor.getValue();
        assertThat(capturedFootballMatch).isEqualTo(stub);
    }

    @Test
    @DisplayName("Ensure the football match id is being passed on to deleteById() from deleteFootballMatchById().")
    void testDeleteFootballMatch() {
        // given
        int footballMatchId = 1;
        given(footballMatchRepository.existsById(footballMatchId))
                .willReturn(true);
        // when
        componentUnderTest.deleteFootballMatchById(footballMatchId);
        //then
        ArgumentCaptor<Integer> footballMatchIdArgumentCaptor = ArgumentCaptor.forClass(int.class);

        verify(footballMatchRepository).deleteById(footballMatchIdArgumentCaptor.capture());

        int capturedFootballMatchId = footballMatchIdArgumentCaptor.getValue();
        assertThat(capturedFootballMatchId).isEqualTo(footballMatchId);
    }

    @Test
    @DisplayName("Ensure FootballMatchNotFoundException is thrown in deleteFootballMatchById().")
    void testDeleteFootballMatchByIdFootballMatchNotFoundException() {
        // given
        int footballMatchId = 1;
        given(footballMatchRepository.existsById(footballMatchId))
                .willReturn(false);
        //then
        assertThatThrownBy(() -> componentUnderTest.deleteFootballMatchById(footballMatchId))
                .isInstanceOf(FootballMatchNotFoundException.class);
    }
}