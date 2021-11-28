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

    @GetMapping("/{footballMatchId}")
    public FootballMatch getFootballMatch(
            @PathVariable("footballMatchId") @PositiveOrZero long footballMatchId) {
        return footballMatchService.getFootballMatchById(footballMatchId);
    }

    @PostMapping
    public ResponseEntity<Object> createFootballMatch(
            @Valid @RequestBody FootballMatch footballMatch) {
        long footballMatchId = footballMatchService.createFootballMatch(footballMatch);
        return new ResponseEntity<>(footballMatchId, CREATED);
    }

    @PutMapping("/{footballMatchId}")
    public ResponseEntity<Object> updateFootballMatch(
            @PathVariable("footballMatchId") @PositiveOrZero long footballMatchId,
            @Valid @RequestBody FootballMatch footballMatch) {
        footballMatchService.updateFootballMatch(footballMatchId, footballMatch);
        return new ResponseEntity<>(NO_CONTENT);
    }

    @DeleteMapping("/{footballMatchId}")
    public ResponseEntity<Object> deleteFootballMatch(
            @PathVariable("footballMatchId") @PositiveOrZero long footballMatchId) {
        footballMatchService.deleteFootballMatchById(footballMatchId);
        return new ResponseEntity<>(NO_CONTENT);
    }
}
