package bg.stoyank.footballtix.task;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ScheduledFuture;

@Service
@AllArgsConstructor
@Slf4j
public class TaskSchedulingService {
    private TaskScheduler taskScheduler;
    private TaskDefinitionBean taskDefinitionBean;

    Map<String, ScheduledFuture<?>> jobsMap = new HashMap<>();

    private static String toCron(Date dateTime) {
        return String.format("0 %s %s %s %s ?",
                dateTime.getMinutes(),
                dateTime.getHours(),
                dateTime.getDate(),
                dateTime.getMonth() + 1);
    }

    public void scheduleATask(TaskDefinition taskDefinition) {
        taskDefinitionBean.setTaskDefinition(taskDefinition);
        String jobId = taskDefinition.getFootballMatchId().toString();
        String cronExpression = toCron(taskDefinition.getExecutionDateTime());
        log.info("Scheduling task with football match id: " + jobId + " and cron expression: " + cronExpression);
        ScheduledFuture<?> scheduledTask = taskScheduler.schedule(
                taskDefinitionBean,
                new CronTrigger(cronExpression,
                        TimeZone.getTimeZone(TimeZone.getDefault().getID())));
        jobsMap.put(jobId, scheduledTask);
    }

    public void removeScheduledTask(String jobId) {
        log.info("Deleting scheduled task with id: " + jobId);
        ScheduledFuture<?> scheduledTask = jobsMap.get(jobId);
        if (scheduledTask != null) {
            scheduledTask.cancel(true);
            jobsMap.put(jobId, null);
        }
    }
}
