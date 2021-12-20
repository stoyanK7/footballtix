package bg.stoyank.footballtix.footballmatch;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.PositiveOrZero;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@AllArgsConstructor
@RequestMapping("/api/matches")
@Validated
public class FootballMatchController {
    private FootballMatchService footballMatchService;
    private ModelMapper modelMapper;

    @GetMapping
    public List<FootballMatch> getAllFootballMatches() {
        return footballMatchService.getAllFootballMatches();
    }

    @GetMapping("/upcoming")
    public List<FootballMatchCardDto> getAllUpcomingFootballMatches() {
        return convertToListOfDto(footballMatchService.getAllUpcomingFootballMatches());
    }

    private FootballMatchCardDto convertToDto(FootballMatch footballMatch) {
        return modelMapper.map(footballMatch, FootballMatchCardDto.class);
    }

    private List<FootballMatchCardDto> convertToListOfDto(List<FootballMatch> matches) {
        List<FootballMatchCardDto> list = new ArrayList<>();
        for (FootballMatch footballMatch : matches) {
            list.add(convertToDto(footballMatch));
        }
        return list;
    }

    @GetMapping("/past")
    public List<FootballMatch> getAllPastFootballMatches() {
        return footballMatchService.getAllPastFootballMatches();
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
