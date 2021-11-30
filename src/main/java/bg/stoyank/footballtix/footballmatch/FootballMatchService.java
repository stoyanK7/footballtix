package bg.stoyank.footballtix.footballmatch;

import bg.stoyank.footballtix.footballmatch.exception.FootballMatchNotFoundException;
import bg.stoyank.footballtix.task.TaskDefinition;
import bg.stoyank.footballtix.task.TaskSchedulingService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class FootballMatchService {
    private FootballMatchRepository footballMatchRepository;
    private TaskSchedulingService taskSchedulingService;

    public List<FootballMatch> getAllFootballMatches() {
        return footballMatchRepository.findAll();
    }

    public List<FootballMatch> getAllUpcomingFootballMatches() {
        return footballMatchRepository
                .getFootballMatchesByStartingDateTimeAfterOrderByStartingDateTimeAsc(new Date());
    }

    public FootballMatch getFootballMatchById(long footballMatchId)
            throws FootballMatchNotFoundException {
        if (footballMatchExistsById(footballMatchId))
            return footballMatchRepository.getById(footballMatchId);

        throw new FootballMatchNotFoundException("Could not find football match with id: " + footballMatchId + ".");
    }

    public long createFootballMatch(FootballMatch footballMatch) {
        log.info("Creating football match: " + footballMatch);
        footballMatchRepository.save(footballMatch);
        scheduleReminderTask(footballMatch);
        return footballMatch.getId();
    }

    public void deleteFootballMatchById(long footballMatchId)
            throws FootballMatchNotFoundException {
        if (!footballMatchExistsById(footballMatchId))
            throw new FootballMatchNotFoundException
                    ("Could not find football match with id: " + footballMatchId + ".");

        log.info("Delete football match with id: " + footballMatchId);
        taskSchedulingService.removeScheduledTask(Long.toString(footballMatchId));
        footballMatchRepository.deleteById(footballMatchId);
    }

    public void subtractTicketsAvailableByOne(long footballMatchId) {
        FootballMatch footballMatch = getFootballMatchById(footballMatchId);
        footballMatch.setTicketsAvailable(footballMatch.getTicketsAvailable() - 1);
        footballMatchRepository.save(footballMatch);
    }

    @Transactional
    public void updateFootballMatch(long footballMatchId, FootballMatch footballMatch) {
        FootballMatch oldFootballMatch = footballMatchRepository.getById(footballMatchId);
        oldFootballMatch.setHomeTeam(footballMatch.getHomeTeam());
        oldFootballMatch.setAwayTeam(footballMatch.getAwayTeam());
        oldFootballMatch.setStartingDateTime(footballMatch.getStartingDateTime());
        oldFootballMatch.setStadium(footballMatch.getStadium());
        oldFootballMatch.setLocation(footballMatch.getLocation());
        oldFootballMatch.setLeague(footballMatch.getLeague());
        oldFootballMatch.setTicketsAvailable(footballMatch.getTicketsAvailable());
        oldFootballMatch.setPricePerTicket(footballMatch.getPricePerTicket());
        footballMatchRepository.save(oldFootballMatch);
    }

    private boolean footballMatchExistsById(long footballMatchId) {
        return footballMatchRepository.existsById(footballMatchId);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void scheduleRemindersAfterStartup() {
        for (FootballMatch footballMatch : getAllUpcomingFootballMatches()) {
            scheduleReminderTask(footballMatch);
        }
    }

    private void scheduleReminderTask(FootballMatch footballMatch) {
        taskSchedulingService.scheduleATask(new TaskDefinition(
                footballMatch.getId(),
                footballMatch.getStartingDateTime(),
                "Send emails for " + footballMatch.getHomeTeam() + " vs " + footballMatch.getAwayTeam()
        ));
    }
}
