package bg.stoyank.footballtix.service;

import bg.stoyank.footballtix.model.FootballMatch;
import bg.stoyank.footballtix.repository.FootballMatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class FootballMatchService {
    private final FootballMatchRepository footballMatchRepository;

    @Autowired

    public FootballMatchService(@Qualifier("FootballMatchRepository") FootballMatchRepository footballMatchRepository) {
        this.footballMatchRepository = footballMatchRepository;
    }

    public List<FootballMatch> getFootballMatches() {
        return footballMatchRepository.findAll();
    }

    public FootballMatch getFootballMatch(Integer footballMatchId) {
        Optional<FootballMatch> footballMatchOptional = footballMatchRepository.findById(footballMatchId);

        if (footballMatchOptional.isPresent()) {
            return footballMatchOptional.get();
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Football match not found!");
    }

    public void addFootballMatch(FootballMatch footballMatch) {
        footballMatchRepository.save(footballMatch);
    }

    public void deleteFootballMatch(Integer footballMatchId) {
        footballMatchRepository.deleteById(footballMatchId);
    }

    @Transactional
    public void updateFootballMatch(Integer footballMatchId, FootballMatch footballMatch) {
        FootballMatch footballMatchToUpdate = this.getFootballMatch(footballMatchId);
        // TO DO
        footballMatchToUpdate.setAwayTeam(footballMatch.getAwayTeam());

    }
}
