package bg.stoyank.footballtix.controller;

import bg.stoyank.footballtix.model.FootballMatch;
import bg.stoyank.footballtix.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("match")
public class MatchController {
    private final MatchService service;

    @Autowired
    public MatchController(MatchService service) {
        this.service = service;
    }

    @PostMapping
    public void createMatch(@RequestBody FootballMatch match) {
        service.addMatch(match);
    }
}
