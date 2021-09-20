package bg.stoyank.footballtix.service;

import bg.stoyank.footballtix.model.FootballMatch;
import bg.stoyank.footballtix.model.exception.FootballMatchNotFoundException;
import bg.stoyank.footballtix.repository.FootballMatchRepository;
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
        if(footballMatchRepository.existsById(footballMatchId)) {
            return footballMatchRepository.getById(footballMatchId);
        }
        throw new FootballMatchNotFoundException();
    }

    public void createFootballMatch(FootballMatch footballMatch) {
        footballMatchRepository.save(footballMatch);
    }

    public void deleteFootballMatchById(int footballMatchId) {
        footballMatchRepository.deleteById(footballMatchId);
    }

    @Transactional
    public void updateFootballMatch(int footballMatchId, FootballMatch footballMatch) {

        // TO DO

    }

}
