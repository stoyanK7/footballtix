package bg.stoyank.footballtix.footballmatch;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class FootballMatchCardDto {
    private Long id;
    private Date startingDateTime;
    private String league;
    private String homeTeam;
    private String awayTeam;
    private String stadium;
    private String location;
}
