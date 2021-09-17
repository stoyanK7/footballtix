package bg.stoyank.footballtix.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class FootballMatch {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String homeTeam;
    private String awayTeam;
    private LocalDateTime startingDateTime;
    private String stadium;
    private String location;
    private String league;

    public FootballMatch() {
    }

    public FootballMatch(String homeTeam,
                         String awayTeam,
                         LocalDateTime startingDateTime,
                         String stadium,
                         String location,
                         String league) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.startingDateTime = startingDateTime;
        this.stadium = stadium;
        this.location = location;
        this.league = league;
    }

    public FootballMatch(Integer id,
                         String homeTeam,
                         String awayTeam,
                         LocalDateTime startingDateTime,
                         String stadium,
                         String location,
                         String league) {
        this.id = id;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.startingDateTime = startingDateTime;
        this.stadium = stadium;
        this.location = location;
        this.league = league;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public LocalDateTime getStartingDateTime() {
        return startingDateTime;
    }

    public void setStartingDateTime(LocalDateTime startingDateTime) {
        this.startingDateTime = startingDateTime;
    }

    public String getStadium() {
        return stadium;
    }

    public void setStadium(String stadium) {
        this.stadium = stadium;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    @Override
    public String toString() {
        return "FootballMatch{" +
                "id=" + id +
                ", homeTeam='" + homeTeam + '\'' +
                ", awayTeam='" + awayTeam + '\'' +
                ", startingDateTime=" + startingDateTime +
                ", stadium='" + stadium + '\'' +
                ", location='" + location + '\'' +
                ", league='" + league + '\'' +
                '}';
    }
}
