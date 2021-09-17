package bg.stoyank.footballtix.controller;

import bg.stoyank.footballtix.model.FootballMatch;
import bg.stoyank.footballtix.service.FootballMatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("match")
public class FootballMatchController {
    private final FootballMatchService footballMatchService;

    @Autowired
    public FootballMatchController(FootballMatchService footballMatchService) {
        this.footballMatchService = footballMatchService;
    }

    @GetMapping
    public List<FootballMatch> getAllFootballMatches() {
        return this.footballMatchService.getFootballMatches();
    }

    @GetMapping(path = "{footballMatchId}")
    public FootballMatch getFootballMatch(@PathVariable("footballMatchId") Integer footballMatchId) {
        return this.footballMatchService.getFootballMatch(footballMatchId);
    }

    @PostMapping
    public void createFootballMatch(@RequestBody FootballMatch footballMatch) {
        this.footballMatchService.addFootballMatch(footballMatch);
    }

    @PutMapping(path = "{footballMatchId}")
    public void updateFootballMatch(@PathVariable("footballMatchId") Integer footballMatchId,
                                    @RequestBody FootballMatch footballMatch) {
        this.footballMatchService.updateFootballMatch(footballMatchId, footballMatch);
    }

    @DeleteMapping(path = "{footballMatchId}")
    public void deleteFootballMatch(@PathVariable("footballMatchId") Integer footballMatchId) {
        this.footballMatchService.deleteFootballMatch(footballMatchId);
    }
}
