package bg.stoyank.footballtix.footballmatch;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface FootballMatchRepository extends JpaRepository<FootballMatch, Integer> {
    List<FootballMatch> getFootballMatchesByStartingDateTimeAfter(LocalDateTime dateTime);
}
