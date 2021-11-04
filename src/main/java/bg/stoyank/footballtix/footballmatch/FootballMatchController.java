package bg.stoyank.footballtix.footballmatch;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/matches")
@Validated
public class FootballMatchController {
    private FootballMatchService footballMatchService;

    @GetMapping
    public List<FootballMatch> getAllFootballMatches() {
        return footballMatchService.getAllFootballMatches();
    }

    @GetMapping("/upcoming")
    public List<FootballMatch> getAllUpcomingFootballMatches() {
        return footballMatchService.getAllUpcomingFootballMatches();
    }

    @GetMapping(path = "/{footballMatchId}")
    public FootballMatch getFootballMatch(@PathVariable("footballMatchId") @PositiveOrZero int footballMatchId) {
        return footballMatchService.getFootballMatchById(footballMatchId);
    }

    @PostMapping
    public ResponseEntity<Object> createFootballMatch(@Valid @RequestBody FootballMatch footballMatch) {
        int footballMatchId = footballMatchService.createFootballMatch(footballMatch);
        return new ResponseEntity<>(footballMatchId, CREATED);
    }

    @PutMapping(path = "/{footballMatchId}")
    public ResponseEntity<Object> updateFootballMatch(@PathVariable("footballMatchId") @PositiveOrZero int footballMatchId,
                                                      @Valid @RequestBody FootballMatch footballMatch) {
        footballMatchService.updateFootballMatch(footballMatchId, footballMatch);
        return new ResponseEntity<>(NO_CONTENT);
    }

    @DeleteMapping(path = "/{footballMatchId}")
    public ResponseEntity<Object> deleteFootballMatch(@PathVariable("footballMatchId") @PositiveOrZero int footballMatchId) {
        footballMatchService.deleteFootballMatchById(footballMatchId);
        return new ResponseEntity<>(NO_CONTENT);
    }
}
