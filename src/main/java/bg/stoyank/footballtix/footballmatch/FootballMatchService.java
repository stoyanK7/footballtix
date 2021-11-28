package bg.stoyank.footballtix.footballmatch;

import bg.stoyank.footballtix.footballmatch.exception.FootballMatchNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class FootballMatchService {
    private FootballMatchRepository footballMatchRepository;

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
        footballMatchRepository.save(footballMatch);
        return footballMatch.getId();
    }

    public void deleteFootballMatchById(long footballMatchId)
            throws FootballMatchNotFoundException {
        if (!footballMatchExistsById(footballMatchId))
            throw new FootballMatchNotFoundException
                    ("Could not find football match with id: " + footballMatchId + ".");

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
}
