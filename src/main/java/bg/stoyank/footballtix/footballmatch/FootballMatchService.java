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

    public FootballMatch getFootballMatchById(int footballMatchId)
            throws FootballMatchNotFoundException {
        if (footballMatchExistsById(footballMatchId))
            return footballMatchRepository.getById(footballMatchId);

        throw new FootballMatchNotFoundException("Could not find football match with id: " + footballMatchId + ".");
    }

    public int createFootballMatch(FootballMatch footballMatch) {
        footballMatchRepository.save(footballMatch);
        return footballMatch.getId();
    }

    public void deleteFootballMatchById(int footballMatchId)
            throws FootballMatchNotFoundException {
        if (!footballMatchExistsById(footballMatchId))
            throw new FootballMatchNotFoundException
                    ("Could not find football match with id: " + footballMatchId + ".");

        footballMatchRepository.deleteById(footballMatchId);
    }

    @Transactional
    public void updateFootballMatch(int footballMatchId, FootballMatch footballMatch) {
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

    private boolean footballMatchExistsById(int footballMatchId) {
        return footballMatchRepository.existsById(footballMatchId);
    }
}
