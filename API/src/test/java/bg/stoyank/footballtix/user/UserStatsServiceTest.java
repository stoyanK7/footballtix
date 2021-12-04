package bg.stoyank.footballtix.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserStatsServiceTest {
    @InjectMocks
    private UserStatsService componentUnderTest;

    @Mock
    private UserStatsRepository userStatsRepository;

    @Test
    @DisplayName("Ensure getAllByDateBetween() invokes repository method.")
    void testGetAllByDateBetween() {
        Date from = any(Date.class);
        Date to = any(Date.class);

        componentUnderTest.getAllByDateBetween(from, to);

        verify(userStatsRepository).getAllByDateBetween(from, to);
    }

    @Test
    @DisplayName("Ensure createNewDayStat() invokes save().")
    void testCreateNewDayStat() {
        UserStats userStats = mock(UserStats.class, RETURNS_DEEP_STUBS);

        componentUnderTest.createNewDayStat(userStats);

        ArgumentCaptor<UserStats> userStatsArgumentCaptor = ArgumentCaptor.forClass(UserStats.class);

        verify(userStatsRepository).save(userStatsArgumentCaptor.capture());

        UserStats capturedValue = userStatsArgumentCaptor.getValue();
        assertThat(capturedValue).isEqualTo(userStats);
    }

    @Test
    @DisplayName("Ensure increaseCountByOne() creates new date if there is none.")
    void testIncreaseCountByOneCreatesNewDate() {
        UserStatsService spy = Mockito.spy(new UserStatsService(userStatsRepository));

        given(userStatsRepository.existsById(any()))
                .willReturn(false);
        given(userStatsRepository.getById(any(Date.class)))
                .willReturn(mock(UserStats.class, RETURNS_DEEP_STUBS));

        spy.increaseCountByOne(new Date());

        verify(spy).createNewDayStat(any(UserStats.class));
    }

    @Test
    @DisplayName("Ensure increaseCountByOne() increases count and saves it.")
    void testIncreaseCountByOne() {
        UserStats userStats = mock(UserStats.class, RETURNS_DEEP_STUBS);

        given(userStatsRepository.existsById(any()))
                .willReturn(false);
        given(userStatsRepository.getById(userStats.getDate()))
                .willReturn(userStats);

        componentUnderTest.increaseCountByOne(userStats.getDate());

        verify(userStatsRepository).save(userStats);
    }
}