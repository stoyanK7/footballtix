package bg.stoyank.footballtix.footballmatch;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/matches")
public class FootballMatchController {
    private final FootballMatchService footballMatchService;

    @GetMapping
    public List<FootballMatch> getAllFootballMatches() {
        return this.footballMatchService.getAllFootballMatches();
    }

    @GetMapping(path = "/{footballMatchId}")
    public FootballMatch getFootballMatch(@PathVariable("footballMatchId") int footballMatchId) {
        return this.footballMatchService.getFootballMatchById(footballMatchId);
    }

    @PostMapping
    public void createFootballMatch(@RequestBody FootballMatch footballMatch) {
        this.footballMatchService.createFootballMatch(footballMatch);
    }

    @PutMapping(path = "/{footballMatchId}")
    public void updateFootballMatch(@PathVariable("footballMatchId") int footballMatchId,
                                    @RequestBody FootballMatch footballMatch) {
        this.footballMatchService.updateFootballMatch(footballMatchId, footballMatch);
    }

    @DeleteMapping(path = "/{footballMatchId}")
    public void deleteFootballMatch(@PathVariable("footballMatchId") int footballMatchId) {
        this.footballMatchService.deleteFootballMatchById(footballMatchId);
    }
}
