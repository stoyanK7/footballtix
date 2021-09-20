package bg.stoyank.footballtix.controller;

import bg.stoyank.footballtix.model.FootballMatch;
import bg.stoyank.footballtix.service.FootballMatchService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/match")
public class FootballMatchController {
    private final FootballMatchService footballMatchService;

    @GetMapping
    public List<FootballMatch> getAllFootballMatches() {
        return this.footballMatchService.getAllFootballMatches();
    }

    @GetMapping(path = "/{footballMatchId}")
    public FootballMatch getFootballMatch(@PathVariable("footballMatchId") Integer footballMatchId) {
        return this.footballMatchService.getFootballMatchById(footballMatchId);
    }

    @PostMapping
    public void createFootballMatch(@RequestBody FootballMatch footballMatch) {
        this.footballMatchService.createFootballMatch(footballMatch);
    }

    @PutMapping(path = "/{footballMatchId}")
    public void updateFootballMatch(@PathVariable("footballMatchId") Integer footballMatchId,
                                    @RequestBody FootballMatch footballMatch) {
        this.footballMatchService.updateFootballMatch(footballMatchId, footballMatch);
    }

    @DeleteMapping(path = "/{footballMatchId}")
    public void deleteFootballMatch(@PathVariable("footballMatchId") Integer footballMatchId) {
        this.footballMatchService.deleteFootballMatchById(footballMatchId);
    }
}
