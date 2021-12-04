package bg.stoyank.footballtix.task;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TaskSchedulingServiceTest {
    @InjectMocks
    private TaskSchedulingService componentUnderTest;

    @Mock
    private TaskScheduler taskScheduler;
    @Mock
    private TaskDefinitionBean taskDefinitionBean;
    @Mock
    private Map<String, ScheduledFuture<?>> jobsMap = new HashMap<>();

    @Test
    @DisplayName("Ensure scheduleATask() sets the taskDefinition and schedules the task.")
    void testScheduleATask() {
        TaskDefinition taskDefinition = mock(TaskDefinition.class, RETURNS_DEEP_STUBS);

        given(taskDefinition.getFootballMatchId())
                .willReturn(1L);
        given(taskDefinition.getExecutionDateTime())
                .willReturn(new Date());

        componentUnderTest.scheduleATask(taskDefinition);

        verify(taskScheduler).schedule(any(), any(CronTrigger.class));
        verify(jobsMap).put(any(), any());
    }

    @Test
    @DisplayName("Ensure removeScheduledTask() works.")
    void testRemoveScheduledTask() {
        String jobId = "1";
        ScheduledFuture scheduledTask = mock(ScheduledFuture.class);

        given(jobsMap.get(anyString()))
                .willReturn(scheduledTask);

        componentUnderTest.removeScheduledTask(jobId);

        verify(jobsMap).get(anyString());
        verify(scheduledTask).cancel(anyBoolean());
        verify(jobsMap).put(any(), any());
    }
}