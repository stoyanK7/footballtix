package bg.stoyank.footballtix.footballmatch;

import bg.stoyank.footballtix.footballmatch.exception.FootballMatchNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class FootballMatchService {
    private final FootballMatchRepository footballMatchRepository;

    public List<FootballMatch> getAllFootballMatches() {
        return footballMatchRepository.findAll();
    }

    public FootballMatch getFootballMatchById(int footballMatchId) {
        if (footballMatchExistsById(footballMatchId)) {
            return footballMatchRepository.getById(footballMatchId);
        }
        throw new FootballMatchNotFoundException();
    }

    public void createFootballMatch(FootballMatch footballMatch) {
        footballMatchRepository.save(footballMatch);
    }

    public void deleteFootballMatchById(int footballMatchId) {
        if (footballMatchExistsById(footballMatchId)) {
            footballMatchRepository.deleteById(footballMatchId);
            return;
        }
        throw new FootballMatchNotFoundException();
    }

    @Transactional
    public void updateFootballMatch(int footballMatchId, FootballMatch footballMatch) {

        // TO DO

    }

    private boolean footballMatchExistsById(int footballMatchId) {
        return footballMatchRepository.existsById(footballMatchId);
    }
}
