package bg.stoyank.footballtix.footballmatch;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/matches")
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
    public FootballMatch getFootballMatch(@PathVariable("footballMatchId") int footballMatchId) {
        System.out.println(footballMatchId);
        return footballMatchService.getFootballMatchById(footballMatchId);
    }

    @PostMapping
    public void createFootballMatch(@RequestBody FootballMatch footballMatch) {
        footballMatchService.createFootballMatch(footballMatch);
    }

    @PutMapping(path = "/{footballMatchId}")
    public ResponseEntity<Object> updateFootballMatch(@PathVariable("footballMatchId") int footballMatchId,
                                                      @RequestBody FootballMatch footballMatch) {
        footballMatchService.updateFootballMatch(footballMatchId, footballMatch);
        return ResponseEntity.ok("Football match with id " + footballMatchId + " updated successfully!");
    }

    @DeleteMapping(path = "/{footballMatchId}")
    public void deleteFootballMatch(@PathVariable("footballMatchId") int footballMatchId) {
        footballMatchService.deleteFootballMatchById(footballMatchId);
    }
}
